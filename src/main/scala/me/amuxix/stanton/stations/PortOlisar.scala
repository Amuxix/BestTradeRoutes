package me.amuxix.stanton.stations

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{Base, CelestialBody, Inhabited, Orbits, SpaceStation, System}

case object PortOlisar extends SpaceStation with Inhabited {
  override lazy val system: System = StantonSystem
  override lazy val orbits: Option[CelestialBody with Orbits] = Some(Crusader)
  override lazy val bases: Set[Base] = Set(me.amuxix.stanton.bases.PortOlisar)
}
