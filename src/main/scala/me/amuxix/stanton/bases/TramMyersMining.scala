package me.amuxix.stanton.bases

import me.amuxix.stanton.moons.Cellin
import me.amuxix.{Aluminum, Alutruciatoxine, CelestialBody, Corundum, Diamond, DistilledSpirits, Laranite, Material, MedicalSupplies, ProcessedFood, Stims, Titanium, TradingPost, WiDoW}

case object TramMyersMining extends TradingPost {
  override val celestialBody: CelestialBody = Cellin
  override val buy: Map[Material, Double] = Map(
    Aluminum -> 1.17,
    Corundum -> 2.41,
    Diamond -> 6.39,
    Laranite -> 28.03,
    Titanium -> 2.1
  )
  override val sell: Map[Material, Double] = Map(
    Alutruciatoxine -> 0,
    DistilledSpirits -> 4.95,
    MedicalSupplies -> 17.97,
    ProcessedFood -> 1.38,
    Stims -> 3.42,
    WiDoW -> 0
  )
}
