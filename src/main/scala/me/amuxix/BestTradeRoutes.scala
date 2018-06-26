package me.amuxix

import me.amuxix.stanton.Bases._

import scala.collection.parallel.ParSeq

object BestTradeRoutes {
  implicit class ExtendedDouble(x: Double) {
    def truncated(n: Int): Double = {
      def p10(n: Int, pow: Long = 10): Long = if (n == 1) pow else p10(n - 1, pow * 10)

      if (n < 0) {
        val m = p10(-n).toDouble
        math.round(x / m) * m
      } else {
        val m = p10(n).toDouble
        math.round(x * m) / m
      }
    }

    def toPercent: String = {
      s"${(x * 100).truncated(2)}%"
    }
  }

  type Jump = (Base, Material, Int, Int, Km)
  val allowIllegal = false
  val bases: Seq[Base] = Seq(PortOlisar, ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility, KudreOre, TerraMillsHydroFarm,
    GaletteFamilyFarms, HickesResearchOutpost, TramMyersMining, GrimHex, ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab, Levski)
  val possibleDestinations: Map[Base, ParSeq[Base]] = bases.map { base =>
    base -> bases.filter(base.canTrade).par
  }.toMap

  val profitToBases: Map[(Base, Base), Map[Material, Double]] = {
    bases.flatMap { base =>
      bases.collect {
        case nextBase if possibleDestinations(base).exists(_ == nextBase) =>
          val profits = base.buy.collect { case (material, cost) if nextBase.sell.contains(material) =>
            material -> (nextBase.sell(material) - cost)
          }
          (base, nextBase) -> profits
      }
    }.toMap
  }

  def main(args: Array[String]): Unit = {
    require(args.length == 2, "Please specify ship and initial investment.")
    val ship = Ship.fromString(args.head)
    val initialInvestment = args(1).toInt

    val startingBase = PortOlisar
    val lookahead = 7
    val maxJumps = 100

    val strings = (20 to (maxJumps, 10)).par.map { jumps =>
      s"Jumps: $jumps => " +
        (1 to lookahead).flatMap { looking =>
          var investment = initialInvestment
          var base: Base = startingBase
            (1 to jumps).flatMap { current =>
              val (nextBase, material, unitsToBuy, profit, _) = calculateNextBestJump(base, possibleDestinations, ship, investment, looking)
              //val unitsToBuyString = s"$unitsToBuy${" " * (ship.cargoSizeInUnits.toString.length - unitsToBuy.toString.length)}"
              investment += profit
              //val string = s"${material.prettyPrint} x $unitsToBuyString -> ${base.prettyPrintNextJump(nextBase)} => $investment aUEC"
              base = nextBase
              //string
              if (current == jumps) {
                Some((looking, investment))
              } else {
                None
              }
            }
        }
          .sortBy(_._2)(Ordering[Int].reverse)
          .map(_._1)
          .mkString(", ")
    }.mkString("\n")
    print(strings)
  }

  def calculateNextBestJump(startingBase: Base, possibleDestinations: Map[Base, ParSeq[Base]], ship: Ship, startingInvestment: Int, lookahead: Int = 1): Jump = {
    def calculateAllJumpsWithMaxLookahead(currentBase: Base, investment: Int, jumps: Seq[Jump]): ParSeq[Seq[Jump]] = {
      possibleDestinations(currentBase)
        .flatMap { nextBase =>
          val (material, profit, unitsToBuy) = currentBase.bestProfit(nextBase, ship, investment)
          val allJumps: Seq[Jump] = jumps :+ (nextBase, material, unitsToBuy, profit, currentBase.distanceTo(nextBase))
          if (allJumps.size == lookahead) {
            Seq(allJumps)
          } else {
            calculateAllJumpsWithMaxLookahead(nextBase, investment + profit, allJumps)
          }
        }
    }
    calculateAllJumpsWithMaxLookahead(startingBase, startingInvestment, Seq.empty).maxBy { jumps =>
      val (totalProfit, totalDistance) = jumps.foldLeft((0, Km(0))) {
        case ((accProfit, accDistance), (_, _, _, profit, distance)) => (accProfit + profit, accDistance + distance)
      }
      totalProfit
    }.head
  }
}