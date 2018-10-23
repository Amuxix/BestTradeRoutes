package me.amuxix.stanton.stations

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.stanton.stars.Stanton

case object PortOlisar extends SpaceStation with Inhabited with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody with Orbits] = Some(Crusader)
  override lazy val bases: Set[Base] = Set(me.amuxix.stanton.bases.PortOlisar)
  override def distances: Map[CelestialBody, Km] = Map(
    Stanton -> Int.MaxValue,
    Crusader -> 22136,
    Cellin -> 70194,
    Daymar -> 42974,
    Yela -> 68397,
    GrimHex -> 68195,
    Delamar -> 854124,
    CommArray306 -> 39561,
    CommArray275 -> 36219,
    CommArray472 -> 66799,
    CommArray126 -> 64874,
    CryAstroService42 -> 36737,
  )
  override protected val absolutePosition: Point = Point(16086, 13500, -7000)
}
