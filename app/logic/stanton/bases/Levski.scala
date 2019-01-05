package logic.stanton.bases

import logic._
import logic.stanton.planets.Delamar

case object Levski extends TradingPost {
  override lazy val celestialBody: CelestialBody = Delamar
  override val buys: Set[Material] = Set(
    AgricultureSupplies,
    Aluminum,
    Iodine,
    Quartz,
  )
  override val sells: Set[Material] = Set(
    Agricium,
    Alutruciatoxine,
    Astatine,
    Beryl,
    Chlorine,
    Corundum,
    Diamond,
    DistilledSpirits,
    Fluorine,
    Gold,
    Hydrogen,
    Laranite,
    MedicalSupplies,
    ProcessedFood,
    Scrap,
    Stims,
    Titanium,
    Tungsten,
    Waste,
    WiDoW,
  )
}
