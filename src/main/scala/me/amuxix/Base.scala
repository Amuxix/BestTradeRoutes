package me.amuxix

import me.amuxix.BestTradeRoutes.{Trade, profitToTradingPosts}
import me.amuxix.stanton.StantonSystem

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

  def distanceFromOrbit: Km = {
    celestialBody match {
      case _: SpaceStation => 10 Km
      case _ => 60 Km
    }
  }

  def distanceTo(other: Base): Distance =
    if (this == other) {
      Distance.zero
    } else {
      val flyingDistance = FlyingDistance(celestialBody.heightOfAtmosphere + other.distanceFromOrbit)
      if (celestialBody == other.celestialBody) {
        Distance(QuantumDistance.zero, flyingDistance)
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
  val buy: Map[Material, Double] //Materials you can buy at this base
  lazy val sold: Set[Material] = buy.keySet.filter(Conditions.materialFilter)
  val sell: Map[Material, Double] //materials you can sell at this base
  lazy val bought: Set[Material] = sell.keySet.filter(Conditions.materialFilter)

  def canTrade(other: TradingPost): Boolean = {
    this != other && buy.exists { case (material, _) =>
      Conditions.materialFilter(material) && other.sell.contains(material)
    }
  }

  def bestProfit(other: TradingPost, ship: Ship, investment: UEC): Option[Trade] = {
    def amountAndProfit(material: Material, unitaryProfit: Double) = {
      val cost = this.buy(material)
      val moneyBuys: Int = (investment.value / cost).toInt
      val amountToBuy = Seq(Some(ship.cargoSizeInUnits), Some(moneyBuys), material.maxSupply, material.maxDemand).flatten.min
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

trait LandingPad {
  val size: Size
}