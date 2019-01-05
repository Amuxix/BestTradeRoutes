package logic.stanton.bases

import logic.stanton.moons.Daymar
import logic.{Alutruciatoxine, Beryl, CelestialBody, DistilledSpirits, Gold, Material, MedicalSupplies, ProcessedFood, Quartz, Stims, TradingPost, Tungsten, WiDoW}

case object ShubinMiningFacility extends TradingPost {
  override lazy val celestialBody: CelestialBody = Daymar
  override val buys: Set[Material] = Set(
    Beryl,
    Gold,
    Quartz,
    Tungsten,
  )
  override val sells: Set[Material] = Set(
    Alutruciatoxine,
    DistilledSpirits,
    MedicalSupplies,
    ProcessedFood,
    Stims,
    WiDoW,
  )
}
