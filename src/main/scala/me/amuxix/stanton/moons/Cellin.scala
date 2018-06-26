package me.amuxix.stanton.moons

import me.amuxix.{Earths, G, Km, Moon}
import me.amuxix.stanton.planets.Crusader

case object Cellin extends Moon {
  override val gravity = G(.357)
  override val atmosphericPressure = Earths(.001)
  override val atmosphereHeight = Km(26)
  override val celestialBody = Crusader
}
