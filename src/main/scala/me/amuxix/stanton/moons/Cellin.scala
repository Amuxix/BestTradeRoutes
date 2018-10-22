package me.amuxix.stanton.moons

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.bases._
import me.amuxix.stanton.planets.Crusader

import scala.language.postfixOps

case object Cellin extends Moon with Atmosphere with Inhabited {
  override lazy val system: System = StantonSystem
  override val gravity: G = .357 G
  override val atmosphericPressure:Earths = .001 Earths
  override val atmosphereHeight: Km = 26 Km
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override lazy val bases: Set[Base] = Set(TerraMillsHydroFarm, HickesResearchOutpost, TramMyersMining)
  override val equatorialRadius: Km = 260 Km
}
