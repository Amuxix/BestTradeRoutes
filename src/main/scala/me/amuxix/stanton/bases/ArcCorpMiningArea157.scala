package me.amuxix.stanton.bases

import me.amuxix.{Alutruciatoxine, Astatine, CelestialBody, Chlorine, DistilledSpirits, Fluorine, Hydrogen, Iodine, Material, MedicalSupplies, ProcessedFood, Stims, TradingPost, WiDoW}
import me.amuxix.stanton.moons.Yela

case object ArcCorpMiningArea157 extends TradingPost {
  override val celestialBody: CelestialBody = Yela
  override val buy: Map[Material, Double] = Map(
    Astatine -> 8.44,
    Chlorine -> 1.44,
    Fluorine -> 2.54,
    Hydrogen -> 0.17,
    Iodine -> 0.41
  )
  override val sell: Map[Material, Double] = Map(
    Alutruciatoxine -> 10.99,
    DistilledSpirits -> 4.95,
    MedicalSupplies -> 17.02,
    ProcessedFood -> 1.38,
    Stims -> 3.42,
    WiDoW -> 27
  )
}
