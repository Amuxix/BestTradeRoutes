package me.amuxix.stanton.planets

import me.amuxix._
import me.amuxix.stanton.Stanton
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.stations.PortOlisar

case object Crusader extends MassiveBody {
  override val orbits: Option[CelestialBody] = Some(Stanton)
  override val orbitedBy: Set[CelestialBody] = Set(Cellin, Daymar, Yela, PortOlisar)
}
