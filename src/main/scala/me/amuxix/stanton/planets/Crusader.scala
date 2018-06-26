package me.amuxix.stanton.planets

import me.amuxix._
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}

case object Crusader extends Planet {
  override val gravity = G(0)
  override val atmosphericPressure = Earths(0)
  override val atmosphereHeight = Km(0)
  override val satellites = Seq(Cellin, Daymar, Yela)
}
