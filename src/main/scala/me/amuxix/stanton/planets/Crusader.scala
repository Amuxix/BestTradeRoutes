package me.amuxix.stanton.planets

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.stars.Stanton
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.stations.PortOlisar

case object Crusader extends Planet {
  override val system: System = StantonSystem
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

  override val atmosphericPressure: Earths = _
  override val atmosphereHeight: Km = _
  override val gravity: G = _
  override val equatorialRadius: Km = _

  override val orbits: Option[CelestialBody] = Some(Stanton)
  override val orbitedBy: Set[CelestialBody with Orbits] = Set(Cellin, Daymar, Yela, PortOlisar)
}
