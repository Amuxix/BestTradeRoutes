package me.amuxix.stanton

import me.amuxix._
import me.amuxix.stanton.moons.{Cellin, Daymar, Yela}
import me.amuxix.stanton.planets.{Crusader, Delamar}

object Bases {
  case object PortOlisar extends SpaceStation(
    Map(
      Fluorine -> 2.65,
      Gold -> 6.05,
      AggricultureSupplies -> 0.75,
      Waste -> 0.45,
      MedicalSupplies -> 17.02,
      Scrap -> 1.52
    ),
    Map(
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
    ),
    Crusader
  )

  case object ArcCorpMiningArea141 extends LandBase(
    Map(
      Agricium -> 24.27,
      Tungsten -> 3.65,
      Quartz -> 1.33,
      Beryl -> 4.09
    ),
    Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    ),
    Daymar,
    OM6,
    0
  )

  case object BountyfulHarvestHydroponics extends LandBase(
    Map(
      ProcessedFood -> 1.31,
      Stims -> 3.17,
      DistilledSpirits -> 4.64
    ),
    Map(
      AggricultureSupplies -> 0.8,
      MedicalSupplies -> 17.97
    ),
    Daymar,
    OM6,
    0
  )

  case object ShubinMiningFacility extends LandBase(
    Map(
      Gold -> 5.71,
      Tungsten -> 3.65,
      Quartz -> 1.33,
      Beryl -> 4.09
    ),
    Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    ),
    Daymar,
    OM1,
    0
  )

  case object KudreOre extends LandBase(
    Map(
      Agricium -> 24.27,
      Gold -> 5.71,
      Tungsten -> 3.65,
      Quartz -> 1.33,
      Beryl -> 4.09
    ),
    Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    ),
    Daymar,
    OM6,
    0
  )

  case object TerraMillsHydroFarm extends LandBase(
    Map(
      ProcessedFood -> 1.31,
      Stims -> 3.17,
      DistilledSpirits -> 4.64
    ),
    Map(
      AggricultureSupplies -> 0.8,
      MedicalSupplies -> 17.97
    ),
    Cellin,
    OM2,
    0
  )

  case object GaletteFamilyFarms extends LandBase(
    Map(
      ProcessedFood -> 1.31,
      Stims -> 3.17,
      DistilledSpirits -> 4.64
    ),
    Map(
      AggricultureSupplies -> 0.8,
      MedicalSupplies -> 17.97
    ),
    Cellin,
    OM1,
    0
  )

  case object HickesResearchOutpost extends LandBase(
    Map(
      AggricultureSupplies -> 0.69
    ),
    Map(
      Astatine -> 8.25,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      Chlorine -> 1.55,
      ProcessedFood -> 1.38
    ),
    Cellin,
    OM6,
    0
  )

  case object TramMyersMining extends LandBase(
    Map(
      Agricium -> 25.72,
      Aluminum -> 1.17,
      Titanium -> 2.1,
      Corundum -> 2.41,
      Diamond -> 6.39,
      Laranite -> 27.45,
      MedicalSupplies -> 17.02
    ),
    Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    ),
    Cellin,
    OM6,
    0
  )

  case object GrimHex extends LandBase(
    Map(
      Gold -> 5.96,
      Diamond -> 6.8,
      Laranite -> 28.54,
      Scrap -> 1.52
    ),
    Map(
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
    ),
    Yela,
    OM6,
    0
  )

  case object ArcCorpMiningArea157 extends LandBase(
    Map(
      Astatine -> 7.74,
      Hydrogen -> 0.12,
      Fluorine -> 2.54,
      Iodine -> 0.35,
      Chlorine -> 1.44
    ),
    Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    ),
    Yela,
    OM2,
    0
  )

  case object BensonMiningOutpost extends LandBase(
    Map(
      Astatine -> 7.74,
      Hydrogen -> 0.12,
      Fluorine -> 2.54,
      Iodine -> 0.35,
      Chlorine -> 1.44
    ),
    Map(
      ProcessedFood -> 1.38,
      Stims -> 3.42,
      DistilledSpirits -> 4.95,
      MedicalSupplies -> 17.97,
      Altruciatoxin -> 11.8,
      WiDoW -> 27
    ),
    Yela,
    OM5,
    0
  )

  case object DeakingReaserchOutpost extends LandBase(
    Map(
      AggricultureSupplies -> 0.69,
      MedicalSupplies -> 17.02
    ),
    Map(
      Astatine -> 8.25,
      Fluorine -> 2.75,
      Iodine -> 0.44,
      Chlorine -> 1.55,
      ProcessedFood -> 1.38
    ),
    Yela,
    OM1,
    0
  )

  case object DrugLab extends LandBase(
    Map(
      Altruciatoxin -> 9.79,
      WiDoW -> 17
    ),
    Map(
      AggricultureSupplies -> 0.8
    ),
    Yela,
    OM4,
    0
  )

  case object Levski extends LandBase(
    Map(
      Iodine -> 0.35,
      Aluminum -> 1.17,
      AggricultureSupplies -> 0.69,
      Quartz -> 1.33
    ),
    Map(
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
    ),
    Delamar,
    OM5,
    0
  )
}
