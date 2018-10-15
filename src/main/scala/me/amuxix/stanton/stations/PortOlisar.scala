package me.amuxix.stanton.stations

import me.amuxix.stanton.planets.Crusader
import me.amuxix.{Base, CelestialBody, SpaceStation, stanton}

case object PortOlisar extends SpaceStation {
  override val orbits: Option[CelestialBody] = Some(Crusader)
  override val bases: Set[Base] = Set(stanton.PortOlisar)
}
