package me.amuxix

abstract class System {
  val center: CelestialBody

  val planets: Set[Planet]
  val moons: Set[Moon]
  val spaceStations: Set[SpaceStation]
  val celestialBodies: Set[CelestialBody] = planets ++ moons ++ spaceStations + center

  val bases: Set[Base] = celestialBodies.collect {
    case celestialBody: CelestialBody with Inhabited => celestialBody.bases
  }.flatten
  val tradingPosts: Set[TradingPost] = celestialBodies.collect {
    case celestialBody: CelestialBody with Inhabited => celestialBody.tradingPosts
  }.flatten
}
