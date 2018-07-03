package me.amuxix

import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}

package object stanton {
  val bases: Seq[Base] = Seq(PortOlisar, ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility, /*KudreOre, */TerraMillsHydroFarm,
    GaletteFamilyFarms, HickesResearchOutpost, TramMyersMining, GrimHex, ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab, Levski)

  val celestialBodies = Seq(Crusader, Delamar, Cellin, Daymar, Yela)

  case object PortOlisar extends Base {
    override val buy: Map[Material, Double] = Map(
      AgricultureSupplies -> 0.75,
      Fluorine -> 2.65,
      Gold -> 6.06,
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
      Corundum -> 2.58,
      Diamond -> 6.8,
      DistilledSpirits -> 4.95,
      Hydrogen -> 0.17,
      Iodine -> 0.44,
      Laranite -> 28.54,
      ProcessedFood -> 1.38,
      Quartz -> 1.46,
      Stims -> 3.38
    )
    override val celestialBody: CelestialBody = stations.PortOlisar
  }

  case object ArcCorpMiningArea141 extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Agricium -> 25.04,
      Beryl -> 4.09,
      Quartz -> 1.33,
      Tungsten -> 3.65
    )
    override val sell: Map[Material, Double] = Map(
      Alutruciatoxine -> 11.8,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      ProcessedFood -> 1.38,
      Stims -> 3.38,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Daymar
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(308)
  }

  case object BountyfulHarvestHydroponics extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      DistilledSpirits -> 4.64,
      ProcessedFood -> 1.31,
      Stims -> 3.17
    )
    override val sell: Map[Material, Double] = Map(
      AgricultureSupplies -> 0.8,
      MedicalSupplies -> 17.02
    )
    override val celestialBody: CelestialBody = Daymar
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(240)
  }

  case object ShubinMiningFacility extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Beryl -> 4.09,
      Gold -> 5.94,
      Quartz -> 1.33,
      Tungsten -> 3.94
    )
    override val sell: Map[Material, Double] = Map(
      Alutruciatoxine -> 10.79,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      ProcessedFood -> 1.38,
      Stims -> 3.42,
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
      Alutruciatoxine -> 11.8,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Daymar
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(377)
  }

  case object TerraMillsHydroFarm extends Base with OnLand  {
    override val buy: Map[Material, Double] = Map(
      DistilledSpirits -> 4.64,
      ProcessedFood -> 1.31,
      Stims -> 3.17
    )
    override val sell: Map[Material, Double] = Map(
      AgricultureSupplies -> 0.8,
      MedicalSupplies -> 17.02
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM2
    override val distanceFromOrbitalMarker: Km = Km(242)
  }

  case object GaletteFamilyFarms extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      DistilledSpirits -> 4.64,
      ProcessedFood -> 1.31,
      Stims -> 3.17
    )
    override val sell: Map[Material, Double] = Map(
      AgricultureSupplies -> 0.8,
      MedicalSupplies -> 17.65
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM1
    override val distanceFromOrbitalMarker: Km = Km(293)
  }

  case object HickesResearchOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      AgricultureSupplies -> 0.69,
      MedicalSupplies -> 17.02
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Chlorine -> 1.56,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      ProcessedFood -> 1.38
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(201)
  }

  case object TramMyersMining extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Aluminum -> 1.17,
      Corundum -> 2.41,
      Diamond -> 6.39,
      Laranite -> 28.54,
      Titanium -> 2.1
    )
    override val sell: Map[Material, Double] = Map(
      Alutruciatoxine -> 11.8,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      ProcessedFood -> 1.38,
      Stims -> 3.34,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Cellin
    override val closestOrbitalMarker: OrbitalMarker = OM6
    override val distanceFromOrbitalMarker: Km = Km(177)
  }

  case object GrimHex extends Base {
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
    override val celestialBody: CelestialBody = stations.GrimHex
  }

  case object ArcCorpMiningArea157 extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Chlorine -> 1.44,
      Fluorine -> 2.54,
      Hydrogen -> 0.17,
      Iodine -> 0.41
    )
    override val sell: Map[Material, Double] = Map(
      Alutruciatoxine -> 10.99,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM2
    override val distanceFromOrbitalMarker: Km = Km(355)
  }

  case object BensonMiningOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Astatine -> 8.17,
      Chlorine -> 1.44,
      Fluorine -> 2.54,
      Hydrogen -> 0.12,
      Iodine -> 0.44
    )
    override val sell: Map[Material, Double] = Map(
      Alutruciatoxine -> 10.47,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.02,
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      WiDoW -> 27
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM5
    override val distanceFromOrbitalMarker: Km = Km(285)
  }

  case object DeakingReaserchOutpost extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      AgricultureSupplies -> 0.69,
      MedicalSupplies -> 17.02
    )
    override val sell: Map[Material, Double] = Map(
      Astatine -> 8.44,
      Chlorine -> 1.56,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      ProcessedFood -> 1.38
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM1
    override val distanceFromOrbitalMarker: Km = Km(366)
  }

  case object DrugLab extends Base with OnLand {
    override val buy: Map[Material, Double] = Map(
      Alutruciatoxine -> 10.77,
      WiDoW -> 26.66
    )
    override val sell: Map[Material, Double] = Map(
      AgricultureSupplies -> 0.8
    )
    override val celestialBody: CelestialBody = Yela
    override val closestOrbitalMarker: OrbitalMarker = OM4
    override val distanceFromOrbitalMarker: Km = Km(351)
  }

  case object Levski extends Base with OnLand {
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
