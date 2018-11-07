package logic.stanton.bases

import logic._
import logic.stanton.stations

case object PortOlisar extends TradingPost {
  override lazy val celestialBody: CelestialBody = stations.PortOlisar
}
