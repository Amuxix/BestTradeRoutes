package me.amuxix.stanton.stations

import me.amuxix
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{Base, CelestialBody, Inhabited, Orbits, SpaceStation, System}

case object PortOlisar extends SpaceStation with Inhabited {
  override val system: System = StantonSystem
  override val orbits: Option[CelestialBody with Orbits] = Some(Crusader)
  override val bases: Set[Base] = Set(me.amuxix.stanton.bases.PortOlisar)
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
  override val equatorialRadius: amuxix.Km = _
}
