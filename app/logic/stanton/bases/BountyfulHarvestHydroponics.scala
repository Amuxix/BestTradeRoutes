package logic.stanton.bases

import logic.stanton.moons.Daymar
import logic.{AgricultureSupplies, CelestialBody, DistilledSpirits, Material, MedicalSupplies, ProcessedFood, Stims, TradingPost}

case object BountyfulHarvestHydroponics extends TradingPost {
  override lazy val celestialBody: CelestialBody = Daymar
}

