package logic.stanton.moons

import logic._
import logic.stanton._
import logic.stanton.bases._
import logic.stanton.planets.Crusader
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object Daymar extends Moon with Atmosphere with Inhabited {
  override lazy val system: System = StantonSystem
  override val gravity: G = .357 G
  override val atmosphericPressure: Earths = 1.45 Earths
  override val atmosphereHeight: Length = 30 km
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override lazy val bases: Set[Base] = Set(ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility)
  override val equatorialRadius: Length = 295 km
  override val charter1Distance: Length = 99776 km
  override val charter2Distance: Length = 42974 km
  override val charter3Distance: Length = 53359 km
  override val charter4Distance: Length = 107131 km
}
