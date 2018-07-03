package me.amuxix

trait Atmosphere {
  val atmosphericPressure: Earths
  val atmosphereHeight: Km
}

trait Gravity {
  val gravity: G
}

sealed trait CelestialBody {
  def heightOfAtmosphere: Km = this match {
    case hasAtmosphere: Atmosphere => hasAtmosphere.atmosphereHeight
    case _ => Km(0)
  }

  def prettyPrint: String = s"$this${" " * (CelestialBody.longestNameLength - this.toString.length)}"
}

sealed trait Satellite extends CelestialBody {
  val celestialBody: CelestialBody
}

abstract class Planet extends CelestialBody with Gravity with Atmosphere {
  override val gravity: G
  override val atmosphericPressure: Earths
  override val atmosphereHeight: Km
  val satellites: Seq[Satellite]
}

abstract class Moon extends Satellite with Gravity with Atmosphere {
  override val gravity: G
  override val atmosphericPressure: Earths
  override val atmosphereHeight: Km
  override val celestialBody: CelestialBody
}

abstract class SpaceStation extends Satellite {
  override val celestialBody: CelestialBody
}

object CelestialBody {
  val celestialBodies: Seq[CelestialBody with Gravity with Atmosphere] = stanton.celestialBodies
  val longestNameLength: Int = celestialBodies.map(_.toString.length).max
}