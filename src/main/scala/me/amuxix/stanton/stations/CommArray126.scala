package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.stanton.stars.Stanton
import me.amuxix.{CelestialBody, Charter, Km, Point, SpaceStation, System}

case object CommArray126 extends SpaceStation with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val distances: Map[CelestialBody, Km] = Map(
    Stanton -> Int.MaxValue,
    Crusader -> 48958,
    PortOlisar -> 64874,
    Cellin -> 64181,
    Daymar -> 99776,
    Yela -> 128246,
    GrimHex -> 128246,
    Delamar -> 916310,
    CommArray306 -> 97917,
    CommArray275 -> 69237,
    CommArray472 -> 69237,
    CryAstroService42 -> 65220,
  )
  override protected val absolutePosition: Point = Point(0, -48958, 0)

}
