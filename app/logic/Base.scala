package logic

import cats.data.OptionT
import cats.implicits._
import logic.BestTradeRoutes.{Trade, profitToTradingPosts}
import logic.util.FindByName
import model.Prices
import squants.space.Length
import squants.space.LengthConversions._

import scala.concurrent._
import scala.language.postfixOps

object Base {
  val filteredTradingPosts: Set[TradingPost] = Universe.tradingPosts.filter(Conditions.tradePostFilter)
  val longestNameLength: Int = Universe.tradingPosts.filter(Conditions.tradePostFilter).map(_.toString.length).max
}

abstract class Base {
  /**
    * The celestial body where this base is located.
    */
  def celestialBody: CelestialBody

  def distanceFromOrbit: Length = {
    celestialBody match {
      case _: SpaceStation => 10 km
      case _ => 60 km
    }
  }

  def distanceTo(other: Base): Distance =
    if (this == other) {
      Distance.Zero
    } else {
      val flyingDistance = FlyingDistance(celestialBody.heightOfAtmosphere + other.distanceFromOrbit)
      if (celestialBody == other.celestialBody) {
        Distance(QuantumDistance.Zero, flyingDistance)
      } else {
        Distance(celestialBody.distanceTo(other.celestialBody), flyingDistance)
      }
    }

  def print: String = s"$this($celestialBody)"

  def prettyPrint: String = s"$this${" " * (Base.longestNameLength - this.toString.length)}"

  def prettyPrintNextJump(nextBase: Base): String = {
    nextBase match {
      case _ if nextBase.celestialBody.isInstanceOf[SpaceStation] =>
        s"${nextBase.prettyPrint}${" " * (CelestialBody.longestNameLength + 3)}"
      case _ =>
        s"${nextBase.prettyPrint} @ ${nextBase.celestialBody.prettyPrint}"
    }
  }
}

/**
  * This represents a base that has a trade terminal that can buy and/or sell materials.
  */
abstract class TradingPost extends Base {
  def buyPrice(material: Material): Future[Double] = Prices.buyPrice(this, material)
  def sellPrice(material: Material): Future[Double] = Prices.sellPrice(this, material)
  val buys: Set[Material]
  val sells: Set[Material]

  def canTrade(other: TradingPost): Boolean = {
    this != other && buys.exists { material =>
      Conditions.materialFilter(material) && other.sells.contains(material)
    }
  }

  def bestProfit(other: TradingPost, ship: Ship, investment: UEC)(implicit ec: ExecutionContext): OptionT[Future, Trade] = {
    def amountAndProfit(material: Material, unitaryProfit: Double): OptionT[Future, (Int, UEC)] = {
      OptionT.liftF(buyPrice(material).map { cost =>
        val moneyBuys: Int = (investment.value / cost).toInt
        val amountToBuy: Int = Seq(Some(ship.cargoSizeInUnits), Some(moneyBuys), material.maxSupply, material.maxDemand).flatten.min
        val profit = UEC((amountToBuy * unitaryProfit).toInt)
        (amountToBuy, profit)
      })
    }

    profitToTradingPosts(this, other).foldLeft(OptionT.none[Future, Trade]){
      case (OptionT.none, (material, Some(unitaryProfit))) =>
        amountAndProfit(material, unitaryProfit).map {
          case (amountToBuy, profit) => (material, profit, amountToBuy)
        }

      case (previous, (material, Some(unitaryProfit))) =>
        for {
          prev @ (_, bestProfit, _) <- previous
          (amountToBuy, profit) <- amountAndProfit(material, unitaryProfit)
        } yield {
          if (profit > bestProfit) {
            (material,  profit, amountToBuy)
          } else {
            prev
          }
        }
      case (previous, _) => previous
    }
  }
}

object TradingPost extends FindByName[TradingPost] {
  override lazy val values: Set[TradingPost] = Universe.tradingPosts
}

trait LandingPad {
  val size: Size
}