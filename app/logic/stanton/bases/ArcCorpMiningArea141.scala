package logic.stanton.bases

import logic._
import logic.stanton.moons.Daymar

case object ArcCorpMiningArea141 extends TradingPost {
  override lazy val celestialBody: CelestialBody = Daymar
  override val buys: Set[Material] = Set(
    Agricium,
    Beryl,
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
