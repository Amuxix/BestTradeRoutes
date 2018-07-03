package me.amuxix.stanton

import me.amuxix
import me.amuxix._
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.Delamar

object Bases {
  case object PortOlisar extends Base {
    override val buy: Map[Material, Double] = Map(
      Fluorine -> 2.65,
      Gold -> 6.06,
      AggricultureSupplies -> 0.75,
      Waste -> 0.09,
      MedicalSupplies -> 17.44,
      Scrap -> 1.52
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Hydrogen -> 0.17,
      Iodine -> 0.44,
      Chlorine -> 1.56,
      Agricium -> 25.72,
      Tungsten -> 3.94,
      Aluminum -> 1.22,
      Titanium -> 2.29,
      Quartz -> 1.46,
      Corundum -> 2.52,
      Diamond -> 6.78,
      Beryl -> 4.3,
      Laranite -> 28.53,
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95
    )
    override val celestialBody: CelestialBody = stations.PortOlisar
  }

  case object ArcCorpMiningArea141 extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Agricium -> 25.73,
      Tungsten -> 3.8,
      Quartz -> 1.40,
      Beryl -> 4.1
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Daymar
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(308)
  }

  case object BountyfulHarvestHydroponics extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      ProcessedFood -> 1.31,
      Stims -> 3.29,
      DistilledSpirits -> 4.64
    )
    override val sell: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.74,
      MedicalSupplies -> 17.02
    )
    override val celestialBody: CelestialBody = Daymar
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(240)
  }

  case object ShubinMiningFacility extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Gold -> 6.08,
      Tungsten -> 3.94,
      Quartz -> 1.33,
      Beryl -> 4.09
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Daymar
    override val closestOrbitalMarker: OrbitalMarker = OM1
    override val distanceFromOrbitalMarker: Km = Km(351)
  }

  //Gone
  case object KudreOre extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Agricium -> 24.27,
      Gold -> 5.71,
      Tungsten -> 3.65,
      Quartz -> 1.33,
      Beryl -> 4.09
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Daymar
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(377)
  }

  case object TerraMillsHydroFarm extends Base with OnLand  {
    override val buy: Map[Material, Double] = Map(
      ProcessedFood -> 1.31,
      Stims -> 3.18,
      DistilledSpirits -> 4.64
    )
    override val sell: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.8,
      MedicalSupplies -> 17.02
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM2
    override val distanceFromOrbitalMarker: Km = Km(242)
  }

  case object GaletteFamilyFarms extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      ProcessedFood -> 1.31,
      Stims -> 3.18,
      DistilledSpirits -> 4.64
    )
    override val sell: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.8,
      MedicalSupplies -> 17.02
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM1
    override val distanceFromOrbitalMarker: Km = Km(293)
  }

  case object HickesResearchOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.80,
      MedicalSupplies -> 17.02
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      Chlorine -> 1.56,
      ProcessedFood -> 1.38
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(201)
  }

  case object TramMyersMining extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Agricium -> 25.72,
      Aluminum -> 1.17,
      Titanium -> 2.21,
      Corundum -> 2.41,
      Diamond -> 6.39,
      Laranite -> 28.54,
      MedicalSupplies -> 17.02
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.81,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(177)
  }

  case object GrimHex extends Base {
    override val buy: Map[Material, Double] = Map(
      Gold -> 5.90,
      Diamond -> 6.8,
      Laranite -> 28.54,
      Scrap -> 1.52,
      Agricium -> 25.72
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Hydrogen -> 0.17,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      Chlorine -> 1.56,
      Tungsten -> 3.94,
      Aluminum -> 1.22,
      Titanium -> 2.29,
      Corundum -> 2.41,
      Quartz -> 1.46,
      Beryl -> 4.3,
      ProcessedFood -> 1.38,
      Waste -> 0.1,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = stations.GrimHex
  }

  case object ArcCorpMiningArea157 extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Hydrogen -> 0.17,
      Fluorine -> 2.64,
      Iodine -> 0.44,
      Chlorine -> 1.56
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM2
    override val distanceFromOrbitalMarker: Km = Km(355)
  }

  case object BensonMiningOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Hydrogen -> 0.17,
      Fluorine -> 2.54,
      Iodine -> 0.44,
      Chlorine -> 1.44
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM5
    override val distanceFromOrbitalMarker: Km = Km(285)
  }

  case object DeakingReaserchOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.80,
      MedicalSupplies -> 17.02
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      Chlorine -> 1.56,
      ProcessedFood -> 1.38
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM1
    override val distanceFromOrbitalMarker: Km = Km(366)
  }

  case object DrugLab extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Altruciatoxin -> 9.79,
      WiDoW -> 26.28
    )
    override val sell: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.0
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM4
    override val distanceFromOrbitalMarker: Km = Km(351)
  }

  case object Levski extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Iodine -> 0.35,
      Aluminum -> 1.17,
      AggricultureSupplies -> 0.69,
      Quartz -> 1.33
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.16,
      Hydrogen -> 0.17,
      Fluorine -> 2.75,
      Chlorine -> 1.55,
      Agricium -> 25.72,
      Gold -> 6.08,
      Tungsten -> 3.94,
      Titanium -> 2.29,
      Corundum -> 2.58,
      Diamond -> 6.8,
      Beryl -> 4.3,
      Laranite -> 28.54,
      ProcessedFood -> 1.38,
      Waste -> 0.5,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Scrap -> 1.6,
      Altruciatoxin -> 11.8,
      WiDoW -> 24.2
    )
    override val celestialBody: CelestialBody = Delamar
    override val closestOrbitalMarker: OrbitalMarker = OM5
    override val distanceFromOrbitalMarker: Km = Km(84)
  }

  /*def fromString(shipName: String): Ship = s"$shipName$$" match {
  PortOlisar
  ArcCorpMiningArea141
  BountyfulHarvestHydroponics
  ShubinMiningFacility
  KudreOre
  TerraMillsHydroFarm
  GaletteFamilyFarms
  HickesResearchOutpost
  TramMyersMining
  GrimHex
  ArcCorpMiningArea157
  BensonMiningOutpost
  DeakingReaserchOutpost
  DrugLab
  Levski
  }*/
}
