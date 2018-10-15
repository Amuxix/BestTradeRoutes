package me.amuxix.stanton.planets

import me.amuxix._
import me.amuxix.stanton.{Levski, Stanton}

case object Delamar extends Planet {
  override val gravity: G = 0 G
  override val atmosphericPressure: Earths = 0 Earths
  override val atmosphereHeight: Km = 7 Km
  override val orbits: Option[CelestialBody] = Some(Stanton)
  override val orbitedBy: Set[CelestialBody] = Set.empty
  override val bases: Set[Base] = Set(Levski)
}
