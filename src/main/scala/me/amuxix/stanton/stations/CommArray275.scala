package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{CelestialBody, SpaceStation, System}

case object CommArray275 extends SpaceStation {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  /*override lazy val distances: Map[CelestialBody, Long] = Map(
    Stanton -> Int.MaxValue,
    Crusader -> 48958,
    PortOlisar -> 36219,
    Cellin -> 0,
    Daymar -> 36410,
    Yela -> 93185,
    GrimHex -> 93096,
    Delamar -> 850323,
    CommArray306 -> 69237,
    CommArray472 -> 97917,
    CommArray126 -> 69238,
    CryAstroService42 -> 70171,
  )
  override protected val absolutePosition: Point = Point(48958, 0, 0)*/
}
