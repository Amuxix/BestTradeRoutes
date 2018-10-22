package me.amuxix.stanton.stars

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.planets.{Crusader, Delamar}
import me.amuxix.{CelestialBody, Km, Orbits, Star, System}

import scala.language.postfixOps

case object Stanton extends Star {
  override lazy val system: System = StantonSystem
  override val equatorialRadius: Km = 0 Km
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set(Crusader, Delamar)
}
