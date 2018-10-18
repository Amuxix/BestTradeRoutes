package me.amuxix.stanton.bases

import me.amuxix.{AgricultureSupplies, Alutruciatoxine, CelestialBody, Material, TradingPost, WiDoW}
import me.amuxix.stanton.moons.Yela

case object DrugLab extends TradingPost {
  override val celestialBody: CelestialBody = Yela
  override val buy: Map[Material, Double] = Map(
    Alutruciatoxine -> 10.77,
    WiDoW -> 26.66
  )
  override val sell: Map[Material, Double] = Map(
    AgricultureSupplies -> 0.8
  )
}
