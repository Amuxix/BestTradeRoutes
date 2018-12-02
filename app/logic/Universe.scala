package logic

import logic.stanton.StantonSystem

object Universe {
  val systems: Set[System] = Set(StantonSystem)

  lazy val planets: Set[Planet] = systems.flatMap(_.planets)
  lazy val moons: Set[Moon] = systems.flatMap(_.moons)
  lazy val spaceStations: Set[SpaceStation] = systems.flatMap(_.spaceStations)
  lazy val celestialBodies: Set[CelestialBody] = systems.map(_.center) ++ planets ++ moons ++ spaceStations

  lazy val bases: Set[Base] = systems.flatMap(_.bases)
  lazy val tradingPosts: Set[TradingPost] = systems.flatMap(_.tradingPosts)
}
