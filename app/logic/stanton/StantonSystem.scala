package logic.stanton

import logic._
import logic.stanton.moons._
import logic.stanton.planets._
import logic.stanton.stars.Stanton
import logic.stanton.stations._

case object StantonSystem extends System(CommArray126, PortOlisar, CommArray306, CommArray472) {
  override val center: CelestialBody = Stanton
  override val planets: Set[Planet] = Set(Crusader, Delamar)
  override val moons: Set[Moon] = Set(Cellin, Daymar, Yela)
  override val spaceStations: Set[SpaceStation] = Set(GrimHex, PortOlisar, CommArray126, CommArray275, CommArray306, CommArray472, CryAstroService42)
}
