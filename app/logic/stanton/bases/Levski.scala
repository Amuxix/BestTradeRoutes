package logic.stanton.bases

import logic._
import logic.stanton.planets.Delamar

case object Levski extends TradingPost {
  override lazy val celestialBody: CelestialBody = Delamar
}
