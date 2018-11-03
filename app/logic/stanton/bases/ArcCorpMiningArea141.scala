package logic.stanton.bases

import logic._
import logic.stanton.moons.Daymar

case object ArcCorpMiningArea141 extends TradingPost {
  override lazy val celestialBody: CelestialBody = Daymar
  override val buy: Map[Material, Double] = Map(
    Agricium -> 24.92,
    Beryl -> 4.09,
    Quartz -> 1.33,
    Tungsten -> 3.65
  )
  override val sell: Map[Material, Double] = Map(
    Alutruciatoxine -> 0,
    DistilledSpirits -> 4.95,
    MedicalSupplies -> 17.37,
    ProcessedFood -> 1.38,
    Stims -> 3.42,
    WiDoW -> 0
  )
}
