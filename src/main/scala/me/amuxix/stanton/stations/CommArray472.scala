package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.stanton.stars.Stanton
import me.amuxix.{CelestialBody, Charter, Km, Point, SpaceStation, System}

case object CommArray472 extends SpaceStation with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val distances: Map[CelestialBody, Km] = Map(
    Stanton -> Int.MaxValue,
    Crusader -> 48958,
    PortOlisar -> 66799,
    Cellin -> 8904,
    Daymar -> 107131,
    Yela -> 93185,
    GrimHex -> 93757,
    Delamar -> 899085,
    CommArray306 -> 69237,
    CommArray275 -> 97917,
    CommArray126 -> 69237,
    CryAstroService42 -> 31816,
  )
  override protected val absolutePosition: Point = Point(-48958, 0, 0)
}
