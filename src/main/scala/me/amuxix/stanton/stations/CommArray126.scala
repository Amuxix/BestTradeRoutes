package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.stanton.stars.Stanton
import me.amuxix.{CelestialBody, Charter, Point, SpaceStation, System}
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object CommArray126 extends SpaceStation with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val distances: Map[CelestialBody, Length] = Map(
    Stanton -> Int.MaxValue.km,
    Crusader -> 48958.km,
    PortOlisar -> 64874.km,
    Cellin -> 64181.km,
    Daymar -> 99776.km,
    Yela -> 128246.km,
    GrimHex -> 128246.km,
    Delamar -> 916310.km,
    CommArray306 -> 97917.km,
    CommArray275 -> 69237.km,
    CommArray472 -> 69237.km,
    CryAstroService42 -> 65220.km,
  )
  override protected val absolutePosition: Point = Point(0 km, -48958 km, 0 km)
}
