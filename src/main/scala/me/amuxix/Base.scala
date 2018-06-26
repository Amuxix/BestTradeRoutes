package me.amuxix

import me.amuxix.BestTradeRoutes.{allowIllegal, profitToBases}
import me.amuxix.stanton.Bases.BountyfulHarvestHydroponics
import me.amuxix.stanton.planets.Crusader


sealed class Base(val buy: Map[Material, Double], val sell: Map[Material, Double], val celestialBody: CelestialBody) {

  def distanceFromOrbit: Int = {
    this match {
      case base: LandBase => base.distanceFromOrbitalMarker
      case _ => 10
    }
  }

  def distanceTo(other: Base): Int = celestialBody.atmosphere + other.distanceFromOrbit

  def prettyPrint: String = s"$this${" " * (BountyfulHarvestHydroponics.toString.length - this.toString.length)}"

  def prettyPrintNextJump(nextBase: Base): String = {
    nextBase match {
      case nextBase: LandBase if this != nextBase =>
        s"${nextBase.prettyPrint} @ ${nextBase.celestialBody.prettyPrint}(${nextBase.closestOrbitalMarker})"
      case _: SpaceStation =>
        s"${nextBase.prettyPrint}   ${" " * (Crusader.toString.length + 5)}"
      case _ =>
        s"${nextBase.prettyPrint} @ ${nextBase.celestialBody.prettyPrint}"
    }
  }

  def canTrade(other: Base): Boolean = {
    this != other && buy.exists { case (material, _) =>
      (allowIllegal || !material.isIllegal) && other.sell.contains(material)
    }
  }

  def bestProfit(other: Base, ship: Ship, investment: Int): (Material, Int, Int) = {
    profitToBases(this, other).map { case (material, profit) =>
      val cost = this.buy(material)
      val moneyBuys: Int = (investment / cost).floor.toInt min ship.cargoSizeInUnits
      val maxStock: Int = material.maxStock.fold(moneyBuys)(_ min moneyBuys)
      (material,  (maxStock * profit).floor.toInt, maxStock)
    }.maxBy(_._2)
  }
}

class LandBase(
  buy: Map[Material, Double],
  sell: Map[Material, Double],
  celestialBody: CelestialBody,
  val closestOrbitalMarker: OrbitalMarker,
  val distanceFromOrbitalMarker: Int
) extends Base(buy, sell, celestialBody)

class SpaceStation(
  buy: Map[Material, Double],
  sell: Map[Material, Double],
  celestialBody: CelestialBody
) extends Base(buy, sell, celestialBody)