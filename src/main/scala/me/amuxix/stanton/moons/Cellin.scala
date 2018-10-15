package me.amuxix.stanton.moons

import me.amuxix.stanton._
import me.amuxix.stanton.planets.Crusader
import me.amuxix.{Base, CelestialBody, Earths, G, Km, Moon}

case object Cellin extends Moon {
  override val gravity: G = .357 G
  override val atmosphericPressure:Earths = .001 Earths
  override val atmosphereHeight: Km = 26 Km
  override val orbits: Option[CelestialBody] = Some(Crusader)
  override val orbitedBy: Set[CelestialBody] = Set.empty
  override val bases: Set[Base] = Set(TerraMillsHydroFarm, HickesResearchOutpost, TramMyersMining)
}
