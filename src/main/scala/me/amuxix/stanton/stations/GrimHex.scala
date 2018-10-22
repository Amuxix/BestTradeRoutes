package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.Yela
import me.amuxix.{Base, CelestialBody, Inhabited, SpaceStation, System}

case object GrimHex extends SpaceStation with Inhabited {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody] = Some(Yela)
  override lazy val bases: Set[Base] = Set(me.amuxix.stanton.bases.GrimHex)
}
