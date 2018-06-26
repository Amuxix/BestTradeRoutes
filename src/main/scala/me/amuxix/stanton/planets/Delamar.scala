package me.amuxix.stanton.planets

import me.amuxix.{Earths, G, Km, Planet}
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}

case object Delamar extends Planet {
  override val gravity = G(0)
  override val atmosphericPressure = Earths(0)
  override val atmosphereHeight = Km(7)
  override val satellites = Seq.empty
}
