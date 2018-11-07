package logic.stanton.bases

import logic.stanton.moons.Yela
import logic.{AgricultureSupplies, Alutruciatoxine, CelestialBody, Material, TradingPost, WiDoW}

case object Jumptown extends TradingPost {
  override lazy val celestialBody: CelestialBody = Yela
}
