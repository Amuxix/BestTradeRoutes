package logic.stanton.bases

import logic.stanton.moons.Yela
import logic.{Alutruciatoxine, Astatine, CelestialBody, Chlorine, DistilledSpirits, Fluorine, Hydrogen, Iodine, Material, MedicalSupplies, ProcessedFood, Stims, TradingPost, WiDoW}

case object ArcCorpMiningArea157 extends TradingPost {
  override lazy val celestialBody: CelestialBody = Yela
  override val buys: Set[Material] = Set(
    Astatine,
    Chlorine,
    Fluorine,
    Hydrogen,
    Iodine,
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
