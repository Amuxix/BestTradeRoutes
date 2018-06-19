object BestTradeRoutes {
  implicit class ExtendedDouble(x: Double) {
    def truncated(n: Int): Double = {
      def p10(n: Int, pow: Long = 10): Long = if (n == 1) pow else p10(n - 1, pow * 10)
      if (n < 0) {
        val m = p10(-n).toDouble
        math.round(x / m) * m
      }
      else {
        val m = p10(n).toDouble
        math.round(x * m) / m
      }
    }

    def toPercent: String = {
      s"${(x * 100).truncated(2)}%"
    }
  }
  val bases: Seq[Base] = Seq(PortOlisar, ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility, KudreOre, TerraMillsHydroFarm, GaletteFamilyFarms,
    HickesResearchOutpost, TramMyersMining, GrimHex, ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab, Levski)

  def main(args: Array[String]): Unit = {
    val initialInvestment = 5000
    val freelancer = Ship(66)
    calculateRoute(bases, freelancer, initialInvestment)
  }

  def calculateRoute(bases: Seq[Base], ship: Ship, initialInvestment: Int, allowIllegal: Boolean = false): Unit = {
    def inner(startingBase: Base, currentBase: Base, remainingBases: Seq[Base], investment: Int, jumps: Seq[(Base, Material, Double)]): Seq[(Seq[(Base, Material, Double)], Double)] = {
      remainingBases.collect {
        case nextBase if nextBase != currentBase =>
          currentBase.bestProfit(nextBase, ship, investment, allowIllegal) match {
            case Some((material, profit)) =>
              val allJumps: Seq[(Base, Material, Double)] = jumps :+ (currentBase, material, (investment + profit).toDouble / investment - 1)
              if (nextBase == startingBase) {
                Seq((allJumps, allJumps.map(_._3).sum / allJumps.length))
              } else {
                inner(startingBase, nextBase, remainingBases diff Seq(nextBase), investment + profit, allJumps)
              }
            case None => Seq.empty
          }
      }.flatten
    }
    /*val folder = new java.io.File(".").getCanonicalPath
    val file = new PrintWriter(new File(folder + File.separatorChar + "results.txt"))*/
    val (jumps, totalProfit) = bases.par.flatMap { base =>
      inner(base, base, bases, initialInvestment, Seq.empty)
    }.maxBy(_._2)
    println(totalProfit.toPercent)
    jumps.foreach { case (base, material, profit) =>
      println(s"$base -> $material(${profit.toPercent})")
    }
    //file.close()
  }
}
