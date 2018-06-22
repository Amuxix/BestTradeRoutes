package me.amuxix

sealed class CelestialBody(val atmosphere: Int)

class Moon(val planet: Planet, atmosphere: Int) extends CelestialBody(atmosphere)
class Planet(atmosphere: Int) extends CelestialBody(atmosphere)