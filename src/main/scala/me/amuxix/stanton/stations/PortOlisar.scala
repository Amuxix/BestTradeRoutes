package me.amuxix.stanton.stations

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.Crusader
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object PortOlisar extends SpaceStation with Inhabited with Charter {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody with Orbits] = Some(Crusader)
  override lazy val bases: Set[Base] = Set(me.amuxix.stanton.bases.PortOlisar)
  override val charter1Distance: Length = 64874 km
  override val charter2Distance: Length = 0 km
  override val charter3Distance: Length = 39561 km
  override val charter4Distance: Length = 66799 km
  override protected val absolutePosition: Point = Point(16086 km, 13500 km, -7000 km)
}
