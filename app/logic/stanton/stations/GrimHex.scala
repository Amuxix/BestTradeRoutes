package logic.stanton.stations

import logic.stanton.StantonSystem
import logic.stanton.moons.Yela
import logic.{Base, CelestialBody, Inhabited, SpaceStation, System}
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object GrimHex extends SpaceStation with Inhabited {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Yela)
  override lazy val bases: Set[Base] = Set(logic.stanton.bases.GrimHex)
  override val charter1Distance: Length = 128246 km
  override val charter2Distance: Length = 68195 km
  override val charter3Distance: Length = 30608 km
  override val charter4Distance: Length = 93757 km
}
