package me.amuxix.stanton.moons

import me.amuxix._
import me.amuxix.stanton._
import me.amuxix.stanton.bases._
import me.amuxix.stanton.planets.Crusader
import me.amuxix.stanton.stations.GrimHex

case object Yela extends Moon with Inhabited {
  override val system: System = StantonSystem
  override val gravity: G = .357 G
  override val atmosphericPressure: Earths = .01 Earths
  override val atmosphereHeight: Km = 31 Km
  override val orbits: Option[CelestialBody] = Some(Crusader)
  override val orbitedBy: Set[CelestialBody with Orbits] = Set(GrimHex)
  override val bases: Set[Base] = Set(ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab)
  override val equatorialRadius: Km = 313 Km
  /**
    * These arguments define the shape and size of the orbital ellipse
    */
  override val periapsis: AU = _
  override val apoapsis: AU = _
  override val eccentricity: Double = _
  /**
    * These arguments define the orientation of the orbital plane.
    */
  override val inclination: Double = _
  override val ascendingNodeAngle: Double = _
  override val periapsisAngle: Double = _
  /**
    * Position at start of time.
    * Currently since planets do not rotate this is considered the planets position.
    */
  override val trueAnomaly: Double = _
}
