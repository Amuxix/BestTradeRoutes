package me.amuxix.stanton

import me.amuxix._
import me.amuxix.stanton.moons._
import me.amuxix.stanton.planets._

case object StantonSystem extends System {
  override val center: CelestialBody = stars.Stanton
  override val planets: Seq[Planet] = Seq(Crusader, Delamar)
  override val moons: Seq[Moon] = Seq(Cellin, Daymar, Yela)
  override val spaceStations: Seq[SpaceStation] = Seq(stations.GrimHex, stations.PortOlisar)
}
