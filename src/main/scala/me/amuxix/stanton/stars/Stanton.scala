package me.amuxix.stanton.stars

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.{CelestialBody, Orbits, Star, System}
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object Stanton extends Star {
  override lazy val system: System = StantonSystem
  override val equatorialRadius: Length = 0 km
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set(Crusader, Delamar)
  override val charter1Distance: Length = Int.MaxValue km
  override val charter2Distance: Length = Int.MaxValue km
  override val charter3Distance: Length = Int.MaxValue km
  override val charter4Distance: Length = Int.MaxValue km
}
