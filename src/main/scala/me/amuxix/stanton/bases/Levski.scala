package me.amuxix.stanton.bases

import me.amuxix._
import me.amuxix.stanton.planets.Delamar

case object Levski extends TradingPost {
  override lazy val celestialBody: CelestialBody = Delamar
  override val buy: Map[Material, Double] = Map(
    AgricultureSupplies -> 0.69,
    Aluminum -> 1.17,
    Iodine -> 0.35,
    Quartz -> 1.33
  )
  override val sell: Map[Material, Double] = Map(
    Agricium -> 25.72,
    Alutruciatoxine -> 0,
    Astatine -> 8.44,
    Beryl -> 4.3,
    Chlorine -> 1.56,
    Corundum -> 2.58,
    Diamond -> 6.8,
    DistilledSpirits -> 4.95,
    Fluorine -> 2.75,
    Gold -> 6.08,
    Hydrogen -> 0.17,
    Laranite -> 28.54,
    MedicalSupplies -> 17.97,
    ProcessedFood -> 1.38,
    Scrap -> 1.6,
    Stims -> 3.42,
    Titanium -> 2.29,
    Tungsten -> 3.94,
    Waste -> 0.1,
    WiDoW -> 0
  )
}
