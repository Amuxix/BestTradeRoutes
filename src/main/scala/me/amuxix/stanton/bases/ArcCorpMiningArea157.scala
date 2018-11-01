package me.amuxix.stanton.bases

import me.amuxix.stanton.moons.Yela
import me.amuxix.{Alutruciatoxine, Astatine, CelestialBody, Chlorine, DistilledSpirits, Fluorine, Hydrogen, Iodine, Material, MedicalSupplies, ProcessedFood, Stims, TradingPost, WiDoW}

case object ArcCorpMiningArea157 extends TradingPost {
  override lazy val celestialBody: CelestialBody = Yela
  override val buy: Map[Material, Double] = Map(
    Astatine -> 7.91,
    Chlorine -> 1.44,
    Fluorine -> 2.54,
    Hydrogen -> 0.12,
    Iodine -> 0.35
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
