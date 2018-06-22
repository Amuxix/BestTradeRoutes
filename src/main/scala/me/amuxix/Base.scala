package me.amuxix

sealed class Base(val buy: Map[Material, Double], val sell: Map[Material, Double], val celestialBody: CelestialBody) {

  def bestProfit(other: Base, ship: Ship, investment: Int, allowIllegal: Boolean = false): Option[(Material, Int)] = {
    this.buy.collect {
      case (material, cost) if other.sell.contains(material) && (!material.isIllegal || allowIllegal) =>
        val moneyBuys: Int = (investment / cost).floor.toInt min ship.cargoSizeInUnits
        val maxStock: Int = material.maxStock.fold(moneyBuys)(_ min moneyBuys)
        material -> (maxStock * (other.sell(material) - cost)).floor.toInt
    } match {
      case empty if empty.isEmpty => None
      case nonEmpty => Some(nonEmpty.maxBy(_._2))
    }
  }

  def distanceFromOrbit: Int = {
    this match {
      case base: LandBase => base.distanceFromOrbitalMarker
      case _ => 0
    }
  }

  def distanceFrom(other: Base): Int = distanceFromOrbit + other.distanceFromOrbit
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