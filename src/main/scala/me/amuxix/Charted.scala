package me.amuxix

/**
  * This contains the distances from all bodies that orbit another given body.
  */
trait Charted { this: CelestialBody =>
  def distance(c1: CelestialBody, c2: CelestialBody): Km = if (c1 == c2) {
    0
  } else {
    if (Set(c1, c2) subsetOf orbitedBy) {
      Math.pow(10, CenterOfUniverse.maxNestedLevel - orbitsToCenterOfUniverse + 3).toInt
    } else {
      throw new Exception(s"$c1 or $c2 do not orbit $this")
    }
  }
}
