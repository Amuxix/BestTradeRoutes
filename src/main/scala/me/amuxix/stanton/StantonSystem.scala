package me.amuxix.stanton

import me.amuxix._
import me.amuxix.stanton.moons._
import me.amuxix.stanton.planets._
import me.amuxix.stanton.stars.Stanton
import me.amuxix.stanton.stations._

case object StantonSystem extends System(CommArray126, PortOlisar, CommArray306, CommArray472) {
  override val center: CelestialBody = Stanton
  override val planets: Set[Planet] = Set(Crusader, Delamar)
  override val moons: Set[Moon] = Set(Cellin, Daymar, Yela)
  override val spaceStations: Set[SpaceStation] = Set(GrimHex, PortOlisar, CommArray126, CommArray275, CommArray306, CommArray472, CryAstroService42)
}
