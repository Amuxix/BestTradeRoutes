package me.amuxix.stanton

import me.amuxix._
import me.amuxix.stanton.planets.{Crusader, Delamar}

case object Stanton extends MassiveBody {
  override val orbits: Option[CelestialBody] = Some(CenterOfUniverse)
  override val orbitedBy: Set[CelestialBody] = Set(Crusader, Delamar)
}
