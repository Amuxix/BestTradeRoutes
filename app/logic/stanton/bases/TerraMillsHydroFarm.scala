package logic.stanton.bases

import logic.stanton.moons.Cellin
import logic.{AgricultureSupplies, CelestialBody, DistilledSpirits, Material, MedicalSupplies, ProcessedFood, Stims, TradingPost}

case object TerraMillsHydroFarm extends TradingPost  {
  override lazy val celestialBody: CelestialBody = Cellin
  override val buys: Set[Material] = Set(
    DistilledSpirits,
    ProcessedFood,
    Stims,
  )
  override val sells: Set[Material] = Set(
    AgricultureSupplies,
    MedicalSupplies,
  )
}
