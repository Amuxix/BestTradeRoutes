import BestTradeRoutes.bases

import scala.annotation.tailrec

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
    val initialInvestment = 50000
    val freelancer = Ship(66)
    val startingBase = PortOlisar
    //calculateRoute(bases, freelancer, initialInvestment)
    val jumps: Seq[(Base, Material, Int)] = calculateNextBestJump(startingBase, bases, freelancer, initialInvestment)
    jumps.foreach { case (base, material, profit) =>
      println(s"$base -> $material: $profit")
    }

  }

  def calculateNextBestJump(startingBase: Base, bases: Seq[Base], ship: Ship, initialInvestment: Int, allowIllegal: Boolean = false) = {
    @tailrec def inner(startBase: Base, remainingBases: Seq[Base], investment: Int, jumps: Seq[(Base, Material, Int)] = Seq.empty): Seq[(Base, Material, Int)] = {
      remainingBases match {
        case Seq() => jumps
        case nextBase :: remainingBases =>
          startBase.bestProfit(nextBase, ship, investment, allowIllegal) match {
            case None => jumps
            case Some((material, profit)) => inner(nextBase, remainingBases.tail, investment + profit, (nextBase, material, profit) +: jumps)
          }
      }
    }
    val possibleBases = bases diff Seq(startingBase)
    possibleBases.permutations.toStream.par.map { combinationBases =>
      //println(s"${combinationBases.head} -> ${combinationBases.tail}")
      val route = inner(combinationBases.head, combinationBases.tail, initialInvestment)
      //println(route)
      route
    } maxBy (_.foldLeft(0)(_ + _._3))
  }

  /*def calculateRoute(bases: Seq[Base], ship: Ship, initialInvestment: Int, allowIllegal: Boolean = false): Unit = {
    def inner(startingBase: Base, currentBase: Base, remainingBases: Seq[Base], investment: Int, jumps: Seq[(Base, Material, Double)]): Seq[(Seq[(Base, Material, Double)], Double)] = {
      remainingBases.collect {
        case nextBase if nextBase != currentBase =>
          currentBase.bestProfit(nextBase, ship, investment, allowIllegal) match {
            case Some((material, profit)) =>
              val allJumps: Seq[(Base, Material, Double)] = jumps :+ (currentBase, material, profit.toDouble / investment)
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
    println(s"Expected returns: ${initialInvestment * (jumps.foldLeft(1.toDouble)((acc, x) => acc * (x._3 + 1)) - 1)}")
    //file.close()
  }*/
}
