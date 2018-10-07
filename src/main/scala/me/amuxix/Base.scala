package me.amuxix

import me.amuxix.BestTradeRoutes.{Trade, allowIllegal, profitToBases}

object Base {
  val bases: Seq[Base] = stanton.bases
  val longestNameLength: Int = bases.map(_.toString.length).max
}

abstract class Base {
  val buy: Map[Material, Double] //Materials you can buy at this base
  lazy val sold: Set[Material] = buy.keySet.filter(BestPriceCheckRoute.materialFilter)
  val sell: Map[Material, Double] //materials you can sell at this base
  lazy val bought: Set[Material] = sell.keySet.filter(BestPriceCheckRoute.materialFilter)
  val celestialBody: CelestialBody

  def distanceFromOrbit: Km = {
    this match {
      case landBase: OnLand => landBase.distanceFromOrbitalMarker
      case _ => 10 Km
    }
  }

  def distanceTo(other: Base): Km = celestialBody.heightOfAtmosphere + other.distanceFromOrbit

  def prettyPrint: String = s"$this${" " * (Base.longestNameLength - this.toString.length)}"

  def prettyPrintNextJump(nextBase: Base): String = {
    nextBase match {
      case _ if nextBase.celestialBody.isInstanceOf[SpaceStation] =>
        s"${nextBase.prettyPrint}${" " * (CelestialBody.longestNameLength + 3)}"
      case _ =>
        s"${nextBase.prettyPrint} @ ${nextBase.celestialBody.prettyPrint}"
    }
  }

  def canTrade(other: Base): Boolean = {
    this != other && buy.exists { case (material, _) =>
      (allowIllegal || !material.isIllegal) && other.sell.contains(material)
    }
  }

  def bestProfit(other: Base, ship: Ship, investment: UEC): Option[Trade] = {
    profitToBases(this, other).collect {
      case (material, Some(profit)) =>
        val cost = this.buy(material)
        val moneyBuys: Int = (investment.value / cost).toInt min ship.cargoSizeInUnits
        val maxStock: Int = material.maxStock.fold(moneyBuys)(_ min moneyBuys)
        (material,  UEC((maxStock * profit).toInt), maxStock)
    } match {
      case Seq() => None
      case nonEmpty: Seq[Trade] => Some(nonEmpty.maxBy(_._2))
    }
  }
}

trait OnLand {
  val closestOrbitalMarker: OrbitalMarker
  val distanceFromOrbitalMarker: Km
}