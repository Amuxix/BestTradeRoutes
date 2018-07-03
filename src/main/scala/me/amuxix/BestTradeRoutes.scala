package me.amuxix

import me.amuxix.Base.bases
import me.amuxix.Material.materials
import me.amuxix.stanton.GrimHex

import scala.annotation.tailrec
import scala.collection.parallel.ParSeq

object BestTradeRoutes {
  type Trade = (Material, UEC, Int)
  type Jump = (Base, Km, Option[Trade])
  val allowIllegal = false

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
    val initialInvestment: UEC = UEC(args(1).toInt * 1000)
    val penetration: Double = args(2).toDouble / 100
    val lookahead = args(3).toInt
    //args.drop(4).mkString("")

    val startingBase = GrimHex
    val maxProfit = bases.flatMap { startingBase =>
      possibleDestinations(startingBase).flatMap(startingBase.bestProfit(_, ship, Int.MaxValue UEC))
    }.foldLeft(UEC(0)){ case (acc, (_, profit,_ )) => acc max profit }

    @tailrec
    def look(base: Base, investment: UEC, previousProfits: Seq[UEC]): Seq[Int] = {
      if (previousProfits.contains(maxProfit)) {
        Seq.empty
      } else {
        val (nextBase, _, trade) = calculateNextBestJump(base, possibleDestinations, ship, investment * penetration, lookahead)
        trade match {
          case Some((material, profit, unitsToBuy)) =>
            val unitsToBuyString = s"$unitsToBuy${" "  * (ship.cargoSizeInUnits.toString.length - unitsToBuy.toString.length)}"
            val currentInvestment = investment + profit
            println(s"${material.prettyPrint} x $unitsToBuyString -> ${base.prettyPrintNextJump(nextBase)} => $currentInvestment(+$profit) aUEC")
            look(nextBase, currentInvestment, previousProfits :+ profit)
          case None =>
            println(s"Nothing${" " * (Material.longestNameLength - 4 + ship.cargoSizeInUnits.toString.length)} -> ${base.prettyPrintNextJump(nextBase)} => $investment(+0) aUEC")
            look(nextBase, investment, previousProfits)
        }

      }
    }

    println(s"Starting at $startingBase with $initialInvestment")
    look(startingBase, initialInvestment, Seq.empty)
  }

  def calculateNextBestJump(startingBase: Base, possibleDestinations: Map[Base, ParSeq[Base]], ship: Ship, startingInvestment: UEC, lookahead: Int = 1): Jump = {
    def calculateAllJumpsWithMaxLookahead(currentBase: Base, investment: UEC, jumps: Seq[Jump]): ParSeq[Seq[Jump]] = {
      possibleDestinations(currentBase)
        .flatMap { nextBase =>
          val trade: Option[Trade] = currentBase.bestProfit(nextBase, ship, investment)
          val allJumps: Seq[Jump] = jumps :+ (nextBase, currentBase.distanceTo(nextBase), trade)
          if (allJumps.size == lookahead) {
            Seq(allJumps)
          } else {
            val profit = trade match {
              case Some((_, profit, _)) => profit
              case None => 0 UEC
            }
            calculateAllJumpsWithMaxLookahead(nextBase, investment + profit, allJumps)
          }
        }
    }
    calculateAllJumpsWithMaxLookahead(startingBase, startingInvestment, Seq.empty).maxBy { jumps =>
      jumps.foldLeft(UEC(0)) {
        case (accProfit, (_, _, Some((_, profit, _)))) => accProfit + profit
        case (accProfit, (_, _, None)) => accProfit
      }
    }.head
  }
}