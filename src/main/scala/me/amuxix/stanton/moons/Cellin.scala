package me.amuxix.stanton.moons

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.bases._
import me.amuxix.stanton.planets.Crusader
import squants.space.Length
import squants.space.LengthConversions._

import scala.language.postfixOps

case object Cellin extends Moon with Atmosphere with Inhabited {
  override lazy val system: System = StantonSystem
  override val gravity: G = .357 G
  override val atmosphericPressure:Earths = .001 Earths
  override val atmosphereHeight: Length = 26 km
  override lazy val orbits: Option[CelestialBody] = Some(Crusader)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override lazy val bases: Set[Base] = Set(TerraMillsHydroFarm, HickesResearchOutpost, TramMyersMining)
  override val equatorialRadius: Length =  260 km
  override val charter1Distance: Length = 64181 km
  override val charter2Distance: Length = 70194 km
  override val charter3Distance: Length = 76477 km
  override val charter4Distance: Length = 8904 km
}
