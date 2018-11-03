package logic.stanton.bases

import logic.stanton.moons.Daymar
import logic.{AgricultureSupplies, CelestialBody, DistilledSpirits, Material, MedicalSupplies, ProcessedFood, Stims, TradingPost}

case object BountyfulHarvestHydroponics extends TradingPost {
  override lazy val celestialBody: CelestialBody = Daymar
  override val buy: Map[Material, Double] = Map(
    DistilledSpirits -> 4.64,
    ProcessedFood -> 1.31,
    Stims -> 3.17
  )
  override val sell: Map[Material, Double] = Map(
    AgricultureSupplies -> 0.8,
    MedicalSupplies -> 17.97
  )
}

