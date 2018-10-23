package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons._
import me.amuxix.stanton.planets._
import me.amuxix.stanton.stars.Stanton
import me.amuxix.{CelestialBody, Charter, Km, Point, SpaceStation, System}

case object CommArray306 extends SpaceStation with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val distances: Map[CelestialBody, Km] = Map(
    Stanton -> Int.MaxValue,
    Crusader -> 48958,
    PortOlisar -> 39561,
    Cellin -> 76477,
    Daymar -> 53359,
    Yela -> 30330,
    GrimHex -> 30608,
    Delamar -> 831707,
    CommArray275 -> 69237,
    CommArray472 -> 69237,
    CommArray126 -> 97917,
    CryAstroService42 -> 41023,
  )
  override protected val absolutePosition: Point = Point(0, 48958, 0)
}
