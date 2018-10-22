package me.amuxix.stanton.planets

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.bases.Levski
import me.amuxix.stanton.stars.Stanton

import scala.language.postfixOps

case object Delamar extends Planet with Atmosphere with Inhabited {
  override lazy val system: System = StantonSystem
  override val gravity: G = 0 G
  override val atmosphericPressure: Earths = 0 Earths
  override val atmosphereHeight: Km = 7 Km
  override lazy val orbits: Option[CelestialBody] = Some(Stanton)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override lazy val bases: Set[Base] = Set(Levski)
  override val equatorialRadius: Km = 75 Km
}
