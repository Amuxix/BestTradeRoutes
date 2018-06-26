package me.amuxix.stanton.moons

import me.amuxix.{Earths, G, Km, Moon}
import me.amuxix.stanton.planets.Crusader

case object Daymar extends Moon {
  override val gravity = G(.357)
  override val atmosphericPressure = Earths(1.45)
  override val atmosphereHeight = Km(30)
  override val celestialBody = Crusader
}
