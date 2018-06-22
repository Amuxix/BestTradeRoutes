package me.amuxix

sealed trait CelestialBody

class Moon(val planet: Planet) extends CelestialBody
class Planet extends CelestialBody