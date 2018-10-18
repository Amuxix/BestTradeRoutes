package me.amuxix.stanton.bases

import me.amuxix.{Alutruciatoxine, Beryl, CelestialBody, DistilledSpirits, Gold, Material, MedicalSupplies, ProcessedFood, Quartz, Stims, TradingPost, Tungsten, WiDoW}
import me.amuxix.stanton.moons.Daymar

case object ShubinMiningFacility extends TradingPost {
  override val celestialBody: CelestialBody = Daymar
  override val buy: Map[Material, Double] = Map(
    Beryl -> 4.09,
    Gold -> 5.94,
    Quartz -> 1.33,
    Tungsten -> 3.94
  )
  override val sell: Map[Material, Double] = Map(
    Alutruciatoxine -> 10.79,
    DistilledSpirits -> 4.95,
    MedicalSupplies -> 17.02,
    ProcessedFood -> 1.38,
    Stims -> 3.42,
    WiDoW -> 27
  )
}
