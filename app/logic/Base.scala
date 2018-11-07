package logic

import logic.BestTradeRoutes.{Trade, profitToTradingPosts}
import logic.stanton.StantonSystem
import logic.stanton.bases._
import logic.util.FindByName
import model.Prices
import squants.space.Length
import squants.space.LengthConversions._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.language.postfixOps

object Base {
  val tradingPosts: Set[TradingPost] = StantonSystem.tradingPosts.filter(Conditions.tradePostFilter)
  val longestNameLength: Int = tradingPosts.map(_.toString.length).max
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
  def buyPrice(material: Material): Double = Await.result(Prices.buyPrice(this, material), Duration.Inf)
  def sellPrice(material: Material): Double = Await.result(Prices.sellPrice(this, material), Duration.Inf)
  val buys: Set[Material]
  val sells: Set[Material]

  def canTrade(other: TradingPost): Boolean = {
    this != other && buys.exists { material =>
      Conditions.materialFilter(material) && other.sells.contains(material)
    }
  }

  def bestProfit(other: TradingPost, ship: Ship, investment: UEC): Option[Trade] = {
    def amountAndProfit(material: Material, unitaryProfit: Double) = {
      val cost = this.buyPrice(material)
      val moneyBuys: Int = (investment.value / cost).toInt
      val amountToBuy: Int = Seq(Some(ship.cargoSizeInUnits), Some(moneyBuys), material.maxSupply, material.maxDemand).flatten.min
      val profit = UEC((amountToBuy * unitaryProfit).toInt)
      (amountToBuy, profit)
    }

    profitToTradingPosts(this, other).foldLeft(None: Option[Trade]){
      case (None, (material, Some(unitaryProfit))) =>
        val (amountToBuy: Int, profit: UEC) = amountAndProfit(material, unitaryProfit)
        Some((material, profit, amountToBuy))
      case (previous @ Some((_, bestProfit, _)), (material, Some(unitaryProfit))) =>
        val (amountToBuy: Int, profit: UEC) = amountAndProfit(material, unitaryProfit)
        if (profit > bestProfit) {
          Some((material,  profit, amountToBuy))
        } else {
          previous
        }
      case (previous, _) => previous
    }
  }
}

object TradingPost extends FindByName[TradingPost] {
  override val values: Seq[TradingPost] = Seq(
    ArcCorpMiningArea141,
    ArcCorpMiningArea157,
    BensonMiningOutpost,
    BountyfulHarvestHydroponics,
    DeakingReaserchOutpost,
    Jumptown,
    GaletteFamilyFarms,
    GrimHex,
    HickesResearchOutpost,
    Levski,
    PortOlisar,
    ShubinMiningFacility,
    TerraMillsHydroFarm,
    TramMyersMining,
  )
}

trait LandingPad {
  val size: Size
}