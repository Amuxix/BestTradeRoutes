package me.amuxix.stanton.moons

import me.amuxix._
import me.amuxix.stanton._
import me.amuxix.stanton.planets.Crusader
import me.amuxix.stanton.stations.GrimHex

case object Yela extends Moon {
  override val gravity: G = .357 G
  override val atmosphericPressure: Earths = .01 Earths
  override val atmosphereHeight: Km = 31 Km
  override val orbits: Option[CelestialBody] = Some(Crusader)
  override val orbitedBy: Set[CelestialBody] = Set(GrimHex)
  override val bases: Set[Base] = Set(ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab)
}
