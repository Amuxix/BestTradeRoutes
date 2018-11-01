package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{CelestialBody, Charter, Point, SpaceStation, System}
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object CommArray126 extends SpaceStation with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override val charter1Distance: Length = 0 km
  override val charter2Distance: Length = 64874 km
  override val charter3Distance: Length = 97917 km
  override val charter4Distance: Length = 69237 km
  override protected val absolutePosition: Point = Point(0 km, -48958 km, 0 km)
}
