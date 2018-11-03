package logic.stanton.bases

import logic._
import logic.stanton.stations

case object PortOlisar extends TradingPost {
  override lazy val celestialBody: CelestialBody = stations.PortOlisar
  override val buy: Map[Material, Double] = Map(
    AgricultureSupplies -> 0.75,
    Fluorine -> 2.65,
    Gold -> 6.08,
    MedicalSupplies -> 17.02,
    Scrap -> 1.52,
    Waste -> 0.09
  )
  override val sell: Map[Material, Double] = Map(
    Agricium -> 25.72,
    Aluminum -> 1.22,
    Astatine -> 8.44,
    Beryl -> 4.3,
    Chlorine -> 1.56,
    Corundum -> 2.8,
    Diamond -> 6.8,
    DistilledSpirits -> 4.95,
    Hydrogen -> 0.17,
    Iodine -> 0.44,
    Laranite -> 28.54,
    ProcessedFood -> 1.38,
    Quartz -> 1.46,
    Stims -> 3.42,
    Titanium -> 2.29,
    Tungsten -> 3.94
  )
}
