package me.amuxix.stanton.stars

import me.amuxix.stanton.StantonSystem
import me.amuxix.{CelestialBody, Km, Orbits, Star, System}
import me.amuxix.stanton.planets.{Crusader, Delamar}

object Stanton extends Star {
  override val system: System = StantonSystem
  override val equatorialRadius: Km = _
  override val orbitedBy: Set[CelestialBody with Orbits] = Set(Crusader, Delamar)
}
