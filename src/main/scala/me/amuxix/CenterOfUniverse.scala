package me.amuxix

import me.amuxix.stanton.Stanton

case object CenterOfUniverse extends MassiveBody with Chart {
  override val orbits: Option[CelestialBody] = None
  override val orbitedBy: Set[CelestialBody] = Set(Stanton)

  lazy val maxNestedLevel: Int = {
    def inner(celestialBody: CelestialBody): Int = celestialBody.orbitedBy.headOption.fold(0)(c => 1 + inner(c))
    inner(this)
  }
}