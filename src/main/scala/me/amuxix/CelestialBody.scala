package me.amuxix

sealed trait Atmosphere {
  val atmosphericPressure: Earths
  val atmosphereHeight: Km
}

sealed trait Gravity {
  val gravity: G
}

sealed abstract class CelestialBody extends Chart {
  val orbits: Option[CelestialBody]
  val orbitedBy: Set[CelestialBody]
  val bases: Set[Base]
  lazy val orbitsToCenterOfUniverse: Int = orbits.fold(0)(_.orbitsToCenterOfUniverse + 1)

  def orbits(celestialBody: CelestialBody): Boolean = orbits.contains(celestialBody)

  def isOrbitedBy(celestialBody: CelestialBody): Boolean = orbitedBy.contains(celestialBody)

  lazy val ancestors: Seq[CelestialBody] = orbits.fold(Seq(this))(parent => this +: parent.ancestors)

  def heightOfAtmosphere: Km = this match {
    case hasAtmosphere: Atmosphere => hasAtmosphere.atmosphereHeight
    case _ => Km(0)
  }

  def prettyPrint: String = s"$this${" " * (CelestialBody.longestNameLength - this.toString.length)}"

  def maxNestedOrbits(orbits: Int = 0) : Int = orbitedBy.map(_.maxNestedOrbits(orbits + 1)).max
}

/**
  * This represents a celestial body with considerable gravity.
  * It can be a gas giant, a sun or even a black hole
  */
abstract class MassiveBody extends CelestialBody {
  override val bases: Set[Base] = Set.empty
}

abstract class Planet extends CelestialBody with Gravity with Atmosphere

sealed abstract class Satellite extends CelestialBody

abstract class Moon extends Satellite with Gravity with Atmosphere

abstract class SpaceStation extends Satellite {
  override val orbitedBy: Set[CelestialBody] = Set.empty
}

object CelestialBody {
  val celestialBodies: Set[CelestialBody] = stanton.celestialBodies
  val longestNameLength: Int = celestialBodies.map(_.toString.length).max
}