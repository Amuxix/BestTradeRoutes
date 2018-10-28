package me.amuxix.stanton.stations

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.stanton.stars.Stanton
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object PortOlisar extends SpaceStation with Inhabited with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody with Orbits] = Some(Crusader)
  override lazy val bases: Set[Base] = Set(me.amuxix.stanton.bases.PortOlisar)
  override def distances: Map[CelestialBody, Length] = Map(
    Stanton -> Int.MaxValue.km,
    Crusader -> 22136.km,
    Cellin -> 70194.km,
    Daymar -> 42974.km,
    Yela -> 68397.km,
    GrimHex -> 68195.km,
    Delamar -> 854124.km,
    CommArray306 -> 39561.km,
    CommArray275 -> 36219.km,
    CommArray472 -> 66799.km,
    CommArray126 -> 64874.km,
    CryAstroService42 -> 36737.km,
  )
  override protected val absolutePosition: Point = Point(16086 km, 13500 km, -7000 km)
}
