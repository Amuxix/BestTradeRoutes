package me.amuxix.stanton.moons

import me.amuxix._
import me.amuxix.stanton.planets.Crusader

case object Yela extends Moon {
  override val gravity = G(.357)
  override val atmosphericPressure = Earths(.01)
  override val atmosphereHeight = Km(31)
  override val celestialBody = Crusader
}
