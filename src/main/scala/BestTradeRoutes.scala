object BestTradeRoutes {
  val bases = Seq(PortOlisar, ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility, KudreOre, TerraMillsHydroFarm, GaletteFamilyFarms,
    HickesResearchOutpost, TramMyersMining, GrimHex, ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab, Levski)
  val astatine = {
    val astatines = bases.flatMap(_.astatine)
    Astatine(astatines.map(_.buyPrice).min, astatines.map(_.sellPrice).max)
  }

  def main(args: Array[String]): Unit = {
    val freelancer = Ship(shipCargoSize = 66, initialInvestment = 5000)
  }
}
