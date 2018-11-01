package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.stanton.stars.Stanton
import me.amuxix.{CelestialBody, Charter, Point, SpaceStation, System}
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object CommArray472 extends SpaceStation with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val distances: Map[CelestialBody, Length] = Map(
    Stanton -> Int.MaxValue.km,
    Crusader -> 48958.km,
    PortOlisar -> 66799.km,
    Cellin -> 8904.km,
    Daymar -> 107131.km,
    Yela -> 93185.km,
    GrimHex -> 93757.km,
    Delamar -> 899085.km,
    CommArray306 -> 69237.km,
    CommArray275 -> 97917.km,
    CommArray126 -> 69237.km,
    CryAstroService42 -> 31816.km,
  )
  override protected val absolutePosition: Point = Point(-48958 km, 0 km, 0 km)
}
