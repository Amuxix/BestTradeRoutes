package logic.stanton.planets

import logic._
import logic.stanton.StantonSystem
import logic.stanton.moons.{Cellin, Daymar, Yela}
import logic.stanton.stars.Stanton
import logic.stanton.stations.PortOlisar
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object Crusader extends Planet {
  override lazy val system: System = StantonSystem

  override val equatorialRadius: Length = 7450 km
  override val gravity: G = 0 G

  override lazy val orbits: Option[CelestialBody] = Some(Stanton)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set(Cellin, Daymar, Yela, PortOlisar)
  override val charter1Distance: Length = 48958 km
  override val charter2Distance: Length = 22136 km
  override val charter3Distance: Length = 48958 km
  override val charter4Distance: Length = 48958 km
}
