package me.amuxix

import me.amuxix.Material.materials
import me.amuxix.stanton.Bases._

import scala.annotation.tailrec
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

  type Trade = (Material, Int, Int)
  type Jump = (Base, Km, Option[Trade])
  val allowIllegal = false
  val bases: Seq[Base] = Seq(PortOlisar, ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility, /*KudreOre, */TerraMillsHydroFarm,
    GaletteFamilyFarms, HickesResearchOutpost, TramMyersMining, GrimHex, ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab, Levski)

  val possibleDestinations: Map[Base, ParSeq[Base]] = bases.map { base =>
    base -> bases.filter(base.canTrade).par
  }.toMap

  val profitToBases: Map[(Base, Base), Seq[(Material, Option[Double])]] =
    bases.flatMap { base =>
      bases.map { nextBase =>
        val profits: Seq[(Material, Option[Double])] = materials.map {
          case material if base.buy.contains(material) && nextBase.sell.contains(material) =>
            (material, Some(nextBase.sell(material) - base.buy(material)))
          case material =>
            (material, None)
        }
        (base, nextBase) -> profits
      }
    }.toMap

  def main(args: Array[String]): Unit = {
    require(args.length == 4, "Please specify ship, initial investment, investment penetration, lookahead and jumps to calculate.")
    val ship = Ship.fromString(args.head)
    val initialInvestment = args(1).toInt * 1000
    val penetration: Float = args(2).toFloat / 100
    val lookahead = args(3).toInt
    //args.drop(4).mkString("")

    val startingBase = GrimHex

    val maxProfit = bases.flatMap { startingBase =>
      possibleDestinations(startingBase).map(startingBase.bestProfit(_, ship, Int.MaxValue)._2)
    }.max

    @tailrec
    def look(base: Base, investment: Int, previousProfits: Seq[Int]): Seq[Int] = {
      if (previousProfits.contains(maxProfit)) {
        Seq.empty
      } else {
        val (nextBase, material, unitsToBuy, profit, _) = calculateNextBestJump(base, possibleDestinations, ship, (investment * penetration).toInt, lookahead)
        val unitsToBuyString = s"$unitsToBuy${" "  * (ship.cargoSizeInUnits.toString.length - unitsToBuy.toString.length)}"
        val currentInvestment = investment + profit
        println(s"${material.prettyPrint} x $unitsToBuyString -> ${base.prettyPrintNextJump(nextBase)} => $currentInvestment(+$profit) aUEC")
        look(nextBase, currentInvestment, previousProfits :+ profit)
      }
    }

    println(s"Starting at $startingBase with $initialInvestment")
    look(startingBase, initialInvestment, Seq.empty)
  }

  def calculateNextBestJump(startingBase: Base, possibleDestinations: Map[Base, ParSeq[Base]], ship: Ship, startingInvestment: Int, lookahead: Int = 1): Jump = {
    def calculateAllJumpsWithMaxLookahead(currentBase: Base, investment: Int, jumps: Seq[Jump]): ParSeq[Seq[Jump]] = {
      possibleDestinations(currentBase)
        .flatMap { nextBase =>
          val trade: Option[Trade] = currentBase.bestProfit(nextBase, ship, investment)
          val allJumps: Seq[Jump] = jumps :+ (nextBase, currentBase.distanceTo(nextBase), trade)
          if (allJumps.size == lookahead) {
            Seq(allJumps)
          } else {
            val profit = trade match {
              case Some((_, profit, _)) => profit
              case None => 0
            }
            calculateAllJumpsWithMaxLookahead(nextBase, investment + profit, allJumps)
          }
        }
    }
    calculateAllJumpsWithMaxLookahead(startingBase, startingInvestment, Seq.empty).maxBy { jumps =>
      jumps.foldLeft(0) {
        case (accProfit, (_, _, Some((_, profit, _)))) => accProfit + profit
        case (accProfit, (_, _, None)) => accProfit
      }
    }.head
  }
}