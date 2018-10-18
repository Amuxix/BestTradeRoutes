package me.amuxix.stanton.bases

import me.amuxix._
import me.amuxix.stanton.moons.Daymar

case object ArcCorpMiningArea141 extends TradingPost {
  override val celestialBody: CelestialBody = Daymar
  override val buy: Map[Material, Double] = Map(
    Agricium -> 25.04,
    Beryl -> 4.09,
    Quartz -> 1.33,
    Tungsten -> 3.65
  )
  override val sell: Map[Material, Double] = Map(
    Alutruciatoxine -> 11.8,
    DistilledSpirits -> 4.95,
    MedicalSupplies -> 17.02,
    ProcessedFood -> 1.38,
    Stims -> 3.38,
    WiDoW -> 27
  )
}
