package me.amuxix.stanton.moons

import me.amuxix._
import me.amuxix.stanton._
import me.amuxix.stanton.bases._
import me.amuxix.stanton.planets.Crusader

import scala.language.postfixOps

case object Daymar extends Moon with Atmosphere with Inhabited {
  override lazy val system: System = StantonSystem
  override val gravity: G = .357 G
  override val atmosphericPressure: Earths = 1.45 Earths
  override val atmosphereHeight: Km = 30 Km
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override lazy val bases: Set[Base] = Set(ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility)
  override val equatorialRadius: Km = 295 Km
}
