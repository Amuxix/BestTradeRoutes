package me.amuxix.stanton.stations

import me.amuxix.stanton.moons.Yela
import me.amuxix.{Base, CelestialBody, SpaceStation, stanton}

case object GrimHex extends SpaceStation {
  override val orbits: Option[CelestialBody] = Some(Yela)
  override val bases: Set[Base] = Set(stanton.GrimHex)
}
