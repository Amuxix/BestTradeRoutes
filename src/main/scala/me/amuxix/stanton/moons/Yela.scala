package me.amuxix.stanton.moons

import me.amuxix._
import me.amuxix.stanton._
import me.amuxix.stanton.bases._
import me.amuxix.stanton.planets.Crusader
import me.amuxix.stanton.stations.GrimHex

import scala.language.postfixOps

case object Yela extends Moon with Atmosphere with Inhabited {
  override lazy val system: System = StantonSystem
  override val gravity: G = .357 G
  override val atmosphericPressure: Earths = .01 Earths
  override val atmosphereHeight: Km = 31 Km
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set(GrimHex)
  override lazy val bases: Set[Base] = Set(ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab)
  override val equatorialRadius: Km = 313 Km
}
