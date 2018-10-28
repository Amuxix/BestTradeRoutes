package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons._
import me.amuxix.stanton.planets._
import me.amuxix.stanton.stars.Stanton
import me.amuxix.{CelestialBody, Charter, Point, SpaceStation, System}
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object CommArray306 extends SpaceStation with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val distances: Map[CelestialBody, Length] = Map(
    Stanton -> Int.MaxValue.km,
    Crusader -> 48958.km,
    PortOlisar -> 39561.km,
    Cellin -> 76477.km,
    Daymar -> 53359.km,
    Yela -> 30330.km,
    GrimHex -> 30608.km,
    Delamar -> 831707.km,
    CommArray275 -> 69237.km,
    CommArray472 -> 69237.km,
    CommArray126 -> 97917.km,
    CryAstroService42 -> 41023.km,
  )
  override protected val absolutePosition: Point = Point(0 km, 48958 km, 0 km)
}
