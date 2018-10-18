package me.amuxix.stanton.bases

import me.amuxix.{AgricultureSupplies, Astatine, CelestialBody, Chlorine, Fluorine, Iodine, Material, MedicalSupplies, ProcessedFood, TradingPost}
import me.amuxix.stanton.moons.Yela

case object DeakingReaserchOutpost extends TradingPost {
  override val celestialBody: CelestialBody = Yela
  override val buy: Map[Material, Double] = Map(
    AgricultureSupplies -> 0.69,
    MedicalSupplies -> 17.02
  )
  override val sell: Map[Material, Double] = Map(
    Astatine -> 8.44,
    Chlorine -> 1.56,
    Fluorine -> 2.75,
    Iodine -> 0.44,
    ProcessedFood -> 1.38
  )
}
