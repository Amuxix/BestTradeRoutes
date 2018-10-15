package me.amuxix

import me.amuxix.stanton.Stanton

/**
  * This is a special celestial body that represents the center of the universe.
  * This orbits around nothing and is the root of all orbits for all the bodies.
  */
case object CenterOfUniverse extends MassiveBody {
  override val orbits: Option[CelestialBody] = None
  override val orbitedBy: Set[CelestialBody] = Set(Stanton)

  /**
    * Maximum orbit nestedness of the modulated universe
    */
  lazy val maxNestedLevel: Int = {
    def inner(celestialBody: CelestialBody): Int = celestialBody.orbitedBy.headOption.fold(0)(c => 1 + inner(c))
    inner(this)
  }
}