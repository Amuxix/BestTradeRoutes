package me.amuxix.stanton.planets

import me.amuxix._
import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.stars.Stanton
import me.amuxix.stanton.stations.PortOlisar

import scala.language.postfixOps

case object Crusader extends Planet {
  override lazy val system: System = StantonSystem

  override val equatorialRadius: Km = 0 Km
  override val gravity: G = 0 G

  override lazy val orbits: Option[CelestialBody] = Some(Stanton)
  override lazy val orbitedBy: Set[CelestialBody with Orbits] = Set(Cellin, Daymar, Yela, PortOlisar)
}
