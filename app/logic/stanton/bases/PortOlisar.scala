package logic.stanton.bases

import logic._
import logic.stanton.stations

case object PortOlisar extends TradingPost {
  override lazy val celestialBody: CelestialBody = stations.PortOlisar
  override val buys: Set[Material] = Set(
    AgricultureSupplies,
    Fluorine,
    Gold,
    MedicalSupplies,
    Scrap,
    Waste,
  )
  override val sells: Set[Material] = Set(
    Agricium,
    Aluminum,
    Astatine,
    Beryl,
    Chlorine,
    Corundum,
    Diamond,
    DistilledSpirits,
    Hydrogen,
    Iodine,
    Laranite,
    ProcessedFood,
    Quartz,
    Stims,
    Titanium,
    Tungsten,
  )
}
