package logic.stanton.bases

import logic.stanton.moons.Yela
import logic.{AgricultureSupplies, Astatine, CelestialBody, Chlorine, Fluorine, Iodine, Material, MedicalSupplies, ProcessedFood, TradingPost}

case object DeakingReaserchOutpost extends TradingPost {
  override lazy val celestialBody: CelestialBody = Yela
}
