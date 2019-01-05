package logic.stanton.moons

import logic._
import logic.stanton._
import logic.stanton.bases._
import logic.stanton.planets.Crusader
import logic.stanton.stations.GrimHex
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object Yela extends Moon with Atmosphere with Inhabited {
  override lazy val system: System = StantonSystem
  override val gravity: G = .357 G
  override val atmosphericPressure: Earths = .01 Earths
  override val atmosphereHeight: Length = 31 km
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set(GrimHex)
  override lazy val bases: Set[Base] = Set(ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, Jumptown)
  override val equatorialRadius: Length = 313 km
  override val charter1Distance: Length = 128246 km
  override val charter2Distance: Length = 68397 km
  override val charter3Distance: Length = 30330 km
  override val charter4Distance: Length = 93185 km
}
