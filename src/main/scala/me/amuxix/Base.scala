package me.amuxix

import me.amuxix.BestTradeRoutes.{allowIllegal, profitToBases}
import me.amuxix.stanton.Bases.BountyfulHarvestHydroponics
import me.amuxix.stanton.planets.{Crusader, Delamar}


abstract class Base {
  val buy: Map[Material, Double]
  val sell: Map[Material, Double]
  val celestialBody: CelestialBody

  def distanceFromOrbit: Km = {
    this match {
      case landBase: OnLand => landBase.distanceFromOrbitalMarker
      case _ => Km(10)
    }
  }

  def distanceTo(other: Base): Km = celestialBody.heightOfAtmosphere + other.distanceFromOrbit

  def prettyPrint: String = s"$this${" " * (BountyfulHarvestHydroponics.toString.length - this.toString.length)}"

  def prettyPrintNextJump(nextBase: Base): String = {
    nextBase match {
      case _ if nextBase.celestialBody.isInstanceOf[SpaceStation] =>
        s"${nextBase.prettyPrint}${" " * (Delamar.toString.length + 4)}"
      case _ =>
        s"${nextBase.prettyPrint} @ ${nextBase.celestialBody.prettyPrint}"
    }
  }

  def canTrade(other: Base): Boolean = {
    this != other && buy.exists { case (material, _) =>
      (allowIllegal || !material.isIllegal) && other.sell.contains(material)
    }
  }

  def bestProfit(other: Base, ship: Ship, investment: Int): Option[(Material, Int, Int)] = {
    profitToBases(this, other).collect {
      case (material, Some(profit)) =>
        val cost = this.buy(material)
        val moneyBuys: Int = (investment / cost).floor.toInt min ship.cargoSizeInUnits
        val maxStock: Int = material.maxStock.fold(moneyBuys)(_ min moneyBuys)
        (material,  (maxStock * profit).floor.toInt, maxStock)
    } match {
      case Seq() => None
      case nonEmpty: Seq[(Material, Int, Int)] => Some(nonEmpty.maxBy(_._2))
    }
  }
}

trait OnLand {
  val closestOrbitalMarker: OrbitalMarker
  val distanceFromOrbitalMarker: Km
}