package me.amuxix.stanton.bases

import me.amuxix.stanton.moons.Daymar
import me.amuxix.{Alutruciatoxine, Beryl, CelestialBody, DistilledSpirits, Gold, Material, MedicalSupplies, ProcessedFood, Quartz, Stims, TradingPost, Tungsten, WiDoW}

case object ShubinMiningFacility extends TradingPost {
  override lazy val celestialBody: CelestialBody = Daymar
  override val buy: Map[Material, Double] = Map(
    Beryl -> 4.09,
    Gold -> 5.71,
    Quartz -> 1.33,
    Tungsten -> 3.65
  )
  override val sell: Map[Material, Double] = Map(
    Alutruciatoxine -> 0,
    DistilledSpirits -> 4.95,
    MedicalSupplies -> 17.97,
    ProcessedFood -> 1.38,
    Stims -> 3.42,
    WiDoW -> 0
  )
}
