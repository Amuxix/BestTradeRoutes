package me.amuxix

/**
  * This contains the distances from all bodies that orbit another given body.
  */
trait Charted { this: CelestialBody =>
  def distance(c1: CelestialBody, c2: CelestialBody): Km = ???
}
