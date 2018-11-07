package logic.stanton.bases

import logic.stanton.stations
import logic.{Agricium, Aluminum, Alutruciatoxine, Astatine, Beryl, CelestialBody, Chlorine, Corundum, Diamond, DistilledSpirits, Fluorine, Gold, Hydrogen, Iodine, Laranite, Material, MedicalSupplies, ProcessedFood, Quartz, Scrap, Stims, Titanium, TradingPost, Tungsten, Waste, WiDoW}

case object GrimHex extends TradingPost {
  override lazy val celestialBody: CelestialBody = stations.GrimHex
}
