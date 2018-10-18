package me.amuxix.stanton.moons

import me.amuxix
import me.amuxix.stanton._
import me.amuxix.stanton.bases._
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{Base, CelestialBody, Earths, G, Inhabited, Km, Moon, Orbits, System}

case object Daymar extends Moon with Inhabited {
  override val system: System = StantonSystem
  override val gravity: G = .357 G
  override val atmosphericPressure: Earths = 1.45 Earths
  override val atmosphereHeight: Km = 30 Km
  override val orbits: Option[CelestialBody] = Some(Crusader)
  override val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override val bases: Set[Base] = Set(ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility)
  override val equatorialRadius: Km = 295 Km
  /**
    * These arguments define the shape and size of the orbital ellipse
    */
  override val periapsis: amuxix.AU = _
  override val apoapsis: amuxix.AU = _
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
