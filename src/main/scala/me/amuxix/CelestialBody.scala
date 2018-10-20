package me.amuxix

import me.amuxix.stanton.StantonSystem

sealed trait Atmosphere {
  val atmosphericPressure: Earths
  val atmosphereHeight: Km
}

sealed trait Gravity {
  val gravity: G
}

trait Orbits {
  val orbits: Option[CelestialBody]

  /**
    * These arguments define the shape and size of the orbital ellipse
    */
  val periapsis: AU
  val apoapsis: AU
  lazy val semimajorAxis: AU = (periapsis + apoapsis) / AU(2)
  val eccentricity: Double

  /**
    * These arguments define the orientation of the orbital plane.
    */
  val inclination: Double
  val ascendingNodeAngle: Double
  val periapsisAngle: Double

  /**
    * Position at start of time.
    * Currently since planets do not rotate this is considered the planets position.
    */
  val trueAnomaly: Double

  def orbits(celestialBody: CelestialBody): Boolean = orbits.contains(celestialBody)
}

trait Inhabited {
  val bases: Set[Base]
  lazy val tradingPosts: Set[TradingPost] = bases.collect {
    case tradingPost: TradingPost => tradingPost
  }
}

sealed abstract class CelestialBody {
  val system: System
  val orbitedBy: Set[CelestialBody with Orbits]
  val equatorialRadius: Km

  def isOrbitedBy(celestialBody: CelestialBody with Orbits): Boolean = orbitedBy.contains(celestialBody)

  def heightOfAtmosphere: Km = this match {
    case hasAtmosphere: Atmosphere => hasAtmosphere.atmosphereHeight
    case _ => Km(0)
  }

  def prettyPrint: String = s"$this${" " * (CelestialBody.longestNameLength - this.toString.length)}"
}

abstract class Star extends CelestialBody

abstract class Planet extends CelestialBody with Gravity with Atmosphere  with Orbits

sealed abstract class Satellite extends CelestialBody with Orbits

abstract class Moon extends Satellite with Gravity with Atmosphere

abstract class SpaceStation extends Satellite {
  override val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
}

object CelestialBody {
  val celestialBodies: Set[CelestialBody] = StantonSystem.celestialBodies
  val longestNameLength: Int = celestialBodies.map(_.toString.length).max
}