package me.amuxix

abstract class System(val c1: Charter, val c2: Charter, val c3: Charter, val c4: Charter) {
  def center: CelestialBody

  def planets: Set[Planet]
  def moons: Set[Moon]
  def spaceStations: Set[SpaceStation]
  lazy val celestialBodies: Set[CelestialBody] = Set(center) ++ planets ++ moons ++ spaceStations

  lazy val bases: Set[Base] = celestialBodies.collect {
    case celestialBody: CelestialBody with Inhabited => celestialBody.bases
  }.flatten
  lazy val tradingPosts: Set[TradingPost] = celestialBodies.collect {
    case celestialBody: CelestialBody with Inhabited => celestialBody.tradingPosts
  }.flatten
}
