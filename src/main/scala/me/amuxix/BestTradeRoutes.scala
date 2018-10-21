package me.amuxix

import me.amuxix.Base.tradingPosts
import me.amuxix.Material.materials
import me.amuxix.stanton.bases.PortOlisar

import scala.annotation.tailrec
import scala.collection.parallel.ParSeq
import scala.language.postfixOps

object BestTradeRoutes {
  type Trade = (Material, UEC, Int)
  type Jump = (TradingPost, Km, Option[Trade])

  val possibleDestinations: Map[TradingPost, ParSeq[TradingPost]] = tradingPosts.map { tradingPost =>
    tradingPost -> tradingPosts.filter(tradingPost.canTrade).toSeq.par
  }.toMap

  val profitToTradingPosts: Map[(TradingPost, TradingPost), Seq[(Material, Option[Double])]] =
    tradingPosts.flatMap { base =>
      tradingPosts.map { nextTradingPost =>
        val profits: Seq[(Material, Option[Double])] = materials.map {
          case material if base.buy.contains(material) && nextTradingPost.sell.contains(material) =>
            (material, Some(nextTradingPost.sell(material) - base.buy(material)))
          case material =>
            (material, None)
        }
        (base, nextTradingPost) -> profits
      }
    }.toMap

  def main(args: Array[String]): Unit = {
    require(args.length == 4, "Please specify ship, initial investment, investment penetration and lookahead.")
    val ship = Ship.fromString(args.head)
    val initialInvestment: UEC = UEC((args(1).toDouble * 1000).toInt)
    val penetration: Double = args(2).toDouble / 100
    val lookahead = args(3).toInt

    val startingTradingPost = PortOlisar

    val maxProfit = tradingPosts.flatMap { startingTradingPost =>
      possibleDestinations(startingTradingPost).flatMap(startingTradingPost.bestProfit(_, ship, Int.MaxValue UEC))
    }.foldLeft(UEC(0)){ case (acc, (_, profit,_ )) => acc max profit }

    @tailrec
    def look(base: TradingPost, investment: UEC): Unit = {
      val (nextTradingPost, _, trade) = calculateNextBestJump(base, possibleDestinations, ship, investment * penetration, lookahead)
      val profit = trade.fold {
        println( s"Nothing${" " * (Material.longestNameLength - 4 + ship.cargoSizeInUnits.toString.length)} -> ${base.prettyPrintNextJump(nextTradingPost)} => $investment(+0) aUEC")
        0 UEC
      } {
        case (material, profit, unitsToBuy) =>
          val unitsToBuyString = s"$unitsToBuy${" "  * (ship.cargoSizeInUnits.toString.length - unitsToBuy.toString.length)}"
          println(s"${material.prettyPrint} x $unitsToBuyString -> ${base.prettyPrintNextJump(nextTradingPost)} => ${investment + profit}(+$profit) aUEC")
          profit
      }
      if (profit != maxProfit) {
        look(nextTradingPost, investment + profit)
      }
    }

    println(s"Starting at $startingTradingPost with $initialInvestment")
    look(startingTradingPost, initialInvestment)
  }

  def calculateNextBestJump(startingTradingPost: TradingPost, possibleDestinations: Map[TradingPost, ParSeq[TradingPost]], ship: Ship, startingInvestment: UEC, lookahead: Int = 1): Jump = {
    def calculateAllJumpsWithMaxLookahead(currentTradingPost: TradingPost, investment: UEC, jumps: Seq[Jump]): ParSeq[Seq[Jump]] = {
      possibleDestinations(currentTradingPost)
        .flatMap { nextTradingPost =>
          val trade: Option[Trade] = currentTradingPost.bestProfit(nextTradingPost, ship, investment)
          val allJumps: Seq[Jump] = jumps :+ (nextTradingPost, currentTradingPost.distanceTo(nextTradingPost), trade)
          if (allJumps.size == lookahead) {
            Seq(allJumps)
          } else {
            calculateAllJumpsWithMaxLookahead(nextTradingPost, trade.fold(investment)(investment + _._2), allJumps)
          }
        }
    }
    calculateAllJumpsWithMaxLookahead(startingTradingPost, startingInvestment, Seq.empty).maxBy { jumps =>
      jumps.foldLeft(UEC(0)) {
        case (accProfit, (_, _, Some((_, profit, _)))) => accProfit + profit
        case (accProfit, (_, _, None)) => accProfit
      }
    }.head
  }
}