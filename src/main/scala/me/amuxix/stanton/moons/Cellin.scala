package me.amuxix.stanton.moons

import me.amuxix
import me.amuxix.stanton.bases._
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{Base, CelestialBody, Earths, G, Inhabited, Km, Moon, Orbits}

case object Cellin extends Moon with Inhabited {
  override val gravity: G = .357 G
  override val atmosphericPressure:Earths = .001 Earths
  override val atmosphereHeight: Km = 26 Km
  override val orbits: Option[CelestialBody] = Some(Crusader)
  override val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override val bases: Set[Base] = Set(TerraMillsHydroFarm, HickesResearchOutpost, TramMyersMining)
  override val equatorialRadius: Km = 260 Km
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
