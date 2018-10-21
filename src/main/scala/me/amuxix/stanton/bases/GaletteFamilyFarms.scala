package me.amuxix.stanton.bases

import me.amuxix.stanton.moons.Cellin
import me.amuxix.{AgricultureSupplies, CelestialBody, DistilledSpirits, Material, MedicalSupplies, ProcessedFood, Stims, TradingPost}

case object GaletteFamilyFarms extends TradingPost {
  override val celestialBody: CelestialBody = Cellin
  override val buy: Map[Material, Double] = Map(
    DistilledSpirits -> 4.64,
    ProcessedFood -> 1.31,
    Stims -> 3.17
  )
  override val sell: Map[Material, Double] = Map(
    AgricultureSupplies -> 0.8,
    MedicalSupplies -> 17.72
  )
}
