package logic

import logic.BestTradeRoutes.{Trade, profitToTradingPosts}
import logic.stanton.StantonSystem
import logic.stanton.bases._
import slick.lifted.MappedTo
import squants.space.Length
import squants.space.LengthConversions._

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
abstract class TradingPost extends Base/* with MappedTo[String]*/ {
  val buy: Map[Material, Double] //Materials you can buy at this base
  lazy val sold: Set[Material] = buy.keySet.filter(Conditions.materialFilter)
  val sell: Map[Material, Double] //materials you can sell at this base
  lazy val bought: Set[Material] = sell.keySet.filter(Conditions.materialFilter)

  //override def value: String = toString

  def canTrade(other: TradingPost): Boolean = {
    this != other && buy.exists { case (material, _) =>
      Conditions.materialFilter(material) && other.sell.contains(material)
    }
  }

  def bestProfit(other: TradingPost, ship: Ship, investment: UEC): Option[Trade] = {
    def amountAndProfit(material: Material, unitaryProfit: Double) = {
      val cost = this.buy(material)
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

object TradingPost {
  def findByName(tradingPost: String): Option[TradingPost] = tradingPost match {
    case s if s.equalsIgnoreCase(ArcCorpMiningArea141.toString) => Some(ArcCorpMiningArea141)
    case s if s.equalsIgnoreCase(ArcCorpMiningArea157.toString) => Some(ArcCorpMiningArea157)
    case s if s.equalsIgnoreCase(BensonMiningOutpost.toString) => Some(BensonMiningOutpost)
    case s if s.equalsIgnoreCase(BountyfulHarvestHydroponics.toString) => Some(BountyfulHarvestHydroponics)
    case s if s.equalsIgnoreCase(DeakingReaserchOutpost.toString) => Some(DeakingReaserchOutpost)
    case s if s.equalsIgnoreCase(DrugLab.toString) => Some(DrugLab)
    case s if s.equalsIgnoreCase(GaletteFamilyFarms.toString) => Some(GaletteFamilyFarms)
    case s if s.equalsIgnoreCase(GrimHex.toString) => Some(GrimHex)
    case s if s.equalsIgnoreCase(HickesResearchOutpost.toString) => Some(HickesResearchOutpost)
    case s if s.equalsIgnoreCase(Levski.toString) => Some(Levski)
    case s if s.equalsIgnoreCase(PortOlisar.toString) => Some(PortOlisar)
    case s if s.equalsIgnoreCase(ShubinMiningFacility.toString) => Some(ShubinMiningFacility)
    case s if s.equalsIgnoreCase(TerraMillsHydroFarm.toString) => Some(TerraMillsHydroFarm)
    case s if s.equalsIgnoreCase(TramMyersMining.toString) => Some(TramMyersMining)
    case _ => None
  }
}

trait LandingPad {
  val size: Size
}