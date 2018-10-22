package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.{CelestialBody, Charter, Km, Point, SpaceStation, System}

case object CommArray275 extends SpaceStation with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val distances: Map[CelestialBody, Km] = Map(
    Crusader -> 48958,
    PortOlisar -> 36219,
    Cellin -> 0,
    Daymar -> 36410,
    Yela -> 93185,
    Delamar -> 850323,
    CommArray306 -> 69237,
    CommArray472 -> 97917,
    CommArray126 -> 69238,
    CryAstroService42 -> 70171,
  )
  override protected val absolutePosition: Point = Point(48958, 0, 0)
}
