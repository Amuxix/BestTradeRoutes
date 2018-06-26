package me.amuxix.stanton

import me.amuxix._
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.Delamar

object Bases {
  case object PortOlisar extends Base {
    override val buy: Map[Material, Double] = Map(
      Fluorine -> 2.65,
      Gold -> 6.05,
      AggricultureSupplies -> 0.75,
      Waste -> 0.45,
      MedicalSupplies -> 17.02,
      Scrap -> 1.52
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.25,
      Hydrogen -> 0.17,
      Iodine -> 0.44,
      Chlorine -> 1.55,
      Agricium -> 25.72,
      Tungsten -> 3.94,
      Aluminum -> 1.22,
      Titanium -> 2.29,
      Quartz -> 1.46,
      Corundum -> 2.58,
      Diamond -> 6.8,
      Beryl -> 4.3,
      Laranite -> 28.54,
      ProcessedFood -> 1.38,
      Stims -> 3.41,
      DistilledSpirits -> 4.95
    )
    override val celestialBody: CelestialBody = stations.PortOlisar
  }

  case object ArcCorpMiningArea141 extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Agricium -> 24.27,
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
    override val distanceFromOrbitalMarker: Km = Km(308)
  }

  case object BountyfulHarvestHydroponics extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      ProcessedFood -> 1.31,
      Stims -> 3.17,
      DistilledSpirits -> 4.64
    )
    override val sell: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.8,
      MedicalSupplies -> 17.97
    )
    override val celestialBody: CelestialBody = Daymar
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(240)
  }

  case object ShubinMiningFacility extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
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
    override val closestOrbitalMarker: OrbitalMarker = OM1
    override val distanceFromOrbitalMarker: Km = Km(351)
  }

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

  case object TerraMillsHydroFarm extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      ProcessedFood -> 1.31,
      Stims -> 3.17,
      DistilledSpirits -> 4.64
    )
    override val sell: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.8,
      MedicalSupplies -> 17.97
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM2
    override val distanceFromOrbitalMarker: Km = Km(242)
  }

  case object GaletteFamilyFarms extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      ProcessedFood -> 1.31,
      Stims -> 3.17,
      DistilledSpirits -> 4.64
    )
    override val sell: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.8,
      MedicalSupplies -> 17.97
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM1
    override val distanceFromOrbitalMarker: Km = Km(293)
  }

  case object HickesResearchOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.69
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.25,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      Chlorine -> 1.55,
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
      Titanium -> 2.1,
      Corundum -> 2.41,
      Diamond -> 6.39,
      Laranite -> 27.45,
      MedicalSupplies -> 17.02
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
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
      Gold -> 5.96,
      Diamond -> 6.8,
      Laranite -> 28.54,
      Scrap -> 1.52
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.25,
      Hydrogen -> 0.17,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      Chlorine -> 1.55,
      Tungsten -> 3.95,
      Aluminum -> 1.22,
      Quartz -> 1.45,
      Beryl -> 4.3,
      ProcessedFood -> 1.38,
      Waste -> 0.5,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = stations.GrimHex
  }

  case object ArcCorpMiningArea157 extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Astatine -> 7.74,
      Hydrogen -> 0.12,
      Fluorine -> 2.54,
      Iodine -> 0.35,
      Chlorine -> 1.44
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM2
    override val distanceFromOrbitalMarker: Km = Km(355)
  }

  case object BensonMiningOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Astatine -> 7.74,
      Hydrogen -> 0.12,
      Fluorine -> 2.54,
      Iodine -> 0.35,
      Chlorine -> 1.44
    )
    override val sell: Map[Material, Double] = Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM5
    override val distanceFromOrbitalMarker: Km = Km(285)
  }

  case object DeakingReaserchOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.69,
      MedicalSupplies -> 17.02
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.25,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      Chlorine -> 1.55,
      ProcessedFood -> 1.38
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM1
    override val distanceFromOrbitalMarker: Km = Km(366)
  }

  case object DrugLab extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Altruciatoxin -> 9.79,
      WiDoW -> 17
    )
    override val sell: Map[Material, Double] = Map(
      AggricultureSupplies -> 0.8
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
}
