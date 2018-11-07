package logic.stanton.bases

import logic.stanton.moons.Cellin
import logic.{Aluminum, Alutruciatoxine, CelestialBody, Corundum, Diamond, DistilledSpirits, Laranite, Material, MedicalSupplies, ProcessedFood, Stims, Titanium, TradingPost, WiDoW}

case object TramMyersMining extends TradingPost {
  override lazy val celestialBody: CelestialBody = Cellin
  override val buys: Set[Material] = Set(
    Aluminum,
    Corundum,
    Diamond,
    Laranite,
    Titanium,
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
