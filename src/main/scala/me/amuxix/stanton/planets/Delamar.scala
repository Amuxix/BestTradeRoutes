package me.amuxix.stanton.planets

import me.amuxix._
import me.amuxix.stanton.bases.Levski
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.stars.Stanton

case object Delamar extends Planet with Inhabited {
  override val system: System = StantonSystem
  override val gravity: G = 0 G
  override val atmosphericPressure: Earths = 0 Earths
  override val atmosphereHeight: Km = 7 Km
  override val orbits: Option[CelestialBody] = Some(Stanton)
  override val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override val bases: Set[Base] = Set(Levski)
  override val equatorialRadius: Km = 75 Km
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
