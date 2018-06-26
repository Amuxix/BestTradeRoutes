package me.amuxix.stanton.stations

import me.amuxix.{CelestialBody, SpaceStation}
import me.amuxix.stanton.planets.Crusader

object PortOlisar extends SpaceStation {
  override val celestialBody: CelestialBody = Crusader
}
