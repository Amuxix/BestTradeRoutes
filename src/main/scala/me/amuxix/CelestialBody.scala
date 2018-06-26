package me.amuxix

import me.amuxix.stanton.planets.Crusader

sealed class CelestialBody(val atmosphere: Int) {
  def prettyPrint: String = s"$this${" " * (Crusader.toString.length - this.toString.length)}"
}

class Moon(val planet: Planet, atmosphere: Int) extends CelestialBody(atmosphere)
class Planet(atmosphere: Int) extends CelestialBody(atmosphere)