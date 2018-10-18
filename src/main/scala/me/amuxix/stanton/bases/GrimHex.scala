package me.amuxix.stanton.bases

import me.amuxix.{Agricium, Aluminum, Alutruciatoxine, Astatine, Beryl, CelestialBody, Chlorine, Corundum, Diamond, DistilledSpirits, Fluorine, Gold, Hydrogen, Iodine, Laranite, Material, MedicalSupplies, ProcessedFood, Quartz, Scrap, Stims, Titanium, TradingPost, Tungsten, Waste, WiDoW}
import me.amuxix.stanton.stations

case object GrimHex extends TradingPost {
  override val celestialBody: CelestialBody = stations.GrimHex
  override val buy: Map[Material, Double] = Map(
    Agricium -> 25.72,
    Diamond -> 6.8,
    Gold -> 5.94,
    Laranite -> 28.54,
    Scrap -> 1.52
  )
  override val sell: Map[Material, Double] = Map(
    Aluminum -> 1.22,
    Alutruciatoxine -> 9.79,
    Astatine -> 8.44,
    Beryl -> 4.3,
    Chlorine -> 1.56,
    Corundum -> 2.58,
    DistilledSpirits -> 4.95,
    Fluorine -> 2.75,
    Hydrogen -> 0.17,
    Iodine -> 0.44,
    MedicalSupplies -> 17.02,
    ProcessedFood -> 1.38,
    Quartz -> 1.46,
    Stims -> 3.33,
    Titanium -> 2.29,
    Tungsten -> 3.94,
    Waste -> 0.1,
    WiDoW -> 16.99
  )
}
