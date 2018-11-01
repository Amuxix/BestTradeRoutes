package me.amuxix.stanton.planets

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.stars.Stanton
import me.amuxix.stanton.stations.PortOlisar
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object Crusader extends Planet {
  override lazy val system: System = StantonSystem

  override val equatorialRadius: Length = 19086.1 km
  override val gravity: G = 0 G

  override lazy val orbits: Option[CelestialBody] = Some(Stanton)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set(Cellin, Daymar, Yela, PortOlisar)
  override val charter1Distance: Length = 48958 km
  override val charter2Distance: Length = 22136 km
  override val charter3Distance: Length = 48958 km
  override val charter4Distance: Length = 48958 km
}
