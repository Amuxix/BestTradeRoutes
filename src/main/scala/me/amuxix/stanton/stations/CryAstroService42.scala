package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{CelestialBody, SpaceStation, System}
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object CryAstroService42 extends SpaceStation {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override val charter1Distance: Length = 65220 km
  override val charter2Distance: Length = 36737 km
  override val charter3Distance: Length = 41023 km
  override val charter4Distance: Length = 31816 km
}
