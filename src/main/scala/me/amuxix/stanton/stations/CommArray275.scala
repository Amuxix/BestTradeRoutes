package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{CelestialBody, SpaceStation, System}
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object CommArray275 extends SpaceStation {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override val charter1Distance: Length = 69237 km
  override val charter2Distance: Length = 36219 km
  override val charter3Distance: Length = 69237 km
  override val charter4Distance: Length = 97917 km
}
