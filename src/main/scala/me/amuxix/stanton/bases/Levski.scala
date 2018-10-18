package me.amuxix.stanton.bases

import me.amuxix.{Agricium, AgricultureSupplies, Aluminum, Alutruciatoxine, Astatine, Beryl, CelestialBody, Chlorine, Corundum, Diamond, DistilledSpirits, Fluorine, Gold, Hydrogen, Iodine, Laranite, Material, MedicalSupplies, ProcessedFood, Quartz, Scrap, Stims, Titanium, TradingPost, Tungsten, Waste, WiDoW}
import me.amuxix.stanton.planets.Delamar

case object Levski extends TradingPost {
  override val celestialBody: CelestialBody = Delamar
  override val buy: Map[Material, Double] = Map(
    AgricultureSupplies -> 0.8,
    Aluminum -> 1.17,
    Iodine -> 0.44,
    Quartz -> 1.4
  )
  override val sell: Map[Material, Double] = Map(
    Agricium -> 25.72,
    Alutruciatoxine -> 9.79,
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
    MedicalSupplies -> 13.32,
    ProcessedFood -> 1.38,
    Scrap -> 1.6,
    Stims -> 3.42,
    Titanium -> 2.29,
    Tungsten -> 3.94,
    Waste -> 0.1,
    WiDoW -> 16.99
  )
}
