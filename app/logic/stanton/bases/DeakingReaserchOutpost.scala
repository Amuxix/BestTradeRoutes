package logic.stanton.bases

import logic.stanton.moons.Yela
import logic.{AgricultureSupplies, Astatine, CelestialBody, Chlorine, Fluorine, Iodine, Material, MedicalSupplies, ProcessedFood, TradingPost}

case object DeakingReaserchOutpost extends TradingPost {
  override lazy val celestialBody: CelestialBody = Yela
  override val buys: Set[Material] = Set(
    AgricultureSupplies,
    MedicalSupplies,
  )
  override val sells: Set[Material] = Set(
    Astatine,
    Chlorine,
    Fluorine,
    Iodine,
    ProcessedFood,
  )
}
