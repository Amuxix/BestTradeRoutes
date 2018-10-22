package me.amuxix.stanton.bases

import me.amuxix.stanton.moons.Yela
import me.amuxix.{AgricultureSupplies, Alutruciatoxine, CelestialBody, Material, TradingPost, WiDoW}

case object DrugLab extends TradingPost {
  override lazy val celestialBody: CelestialBody = Yela
  override val buy: Map[Material, Double] = Map(
    Alutruciatoxine -> 0,
    WiDoW -> 0
  )
  override val sell: Map[Material, Double] = Map(
    AgricultureSupplies -> 0
  )
}
