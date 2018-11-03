package logic

import logic.Base.tradingPosts
import logic.stanton.bases._

import scala.language.postfixOps

object BestPriceCheckRoute {
  type PriceCheckMap = Map[TradingPost, Set[Material]]

  val tradingPostsToCheck: Seq[TradingPost] = tradingPosts.toSeq.sortBy(tradingPost => (tradingPost.bought.size, -tradingPost.sold.size))
  val totalPrices: Int = tradingPostsToCheck.foldLeft(0)((prices, tradingPost) =>
    prices + tradingPost.sold.size + tradingPost.bought.size
  )

  val startingBase: TradingPost = GrimHex
  val knownBases: Seq[TradingPost] = Seq(BountyfulHarvestHydroponics, TerraMillsHydroFarm, GaletteFamilyFarms, HickesResearchOutpost,
    ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost)

  def main(args: Array[String]): Unit = {
    var bestRouteSize = totalPrices * 2
    var bestRouteDistance = Distance.MaxValue
    //val existsTradingPostThatBuysNothing = tradingPostsToCheck.exists(_.sold.isEmpty)
    /**
      * If there exists a trading post that does not buy anything it might be possible to start at that trading post and visit all other trading posts once.
      * If such trading post does not exist that first trading post will have to be visited once.
      */
    //val theoreticalRouteMinimum = tradingPostsToCheck.size + (if (existsTradingPostThatBuysNothing) 0 else 1)


    def look(buyPriceCheckMap: PriceCheckMap, sellPriceCheckMap: PriceCheckMap, materialsInHull: Set[Material], pricesChecked: Int, previousTradingPosts: Seq[TradingPost], distance: Distance): Any = {
      if (pricesChecked == totalPrices && distance < bestRouteDistance) {
        bestRouteSize = previousTradingPosts.size
        bestRouteDistance = distance
        println(s"$bestRouteSize, $bestRouteDistance -> ${previousTradingPosts.map(tradingPost => s"$tradingPost(${tradingPost.celestialBody.toString})").mkString(", ")}")
      } else if (previousTradingPosts.size < bestRouteSize) {
        // If the number of trading posts visited is bestRouteSize - 1 and we still need to check more prices this solution will not be better than one we already have.
        tradingPostsToCheck.collect {
          case tradingPost if previousTradingPosts.count(_ == tradingPost) <= 2 && (distance + previousTradingPosts.last.distanceTo(tradingPost)) < bestRouteDistance => // At most we only need to visit a trading post twice
            val (updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked) = updatePriceCheckMaps(tradingPost, buyPriceCheckMap, sellPriceCheckMap, materialsInHull)
            if (updatedPricesChecked > pricesChecked || updatedMaterialsInHull.size > materialsInHull.size) { //Discard trading posts that give no new prices or materials.
              look(updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked, previousTradingPosts :+ tradingPost, distance + previousTradingPosts.last.distanceTo(tradingPost))
            }
        }
      }
    }
    val buyPriceCheckMap: PriceCheckMap = (knownBases :+ startingBase).map { base =>
      base -> base.sold
    }.toMap
    val sellPriceCheckMap: PriceCheckMap = knownBases.map { base =>
      base -> base.bought
    }.toMap
    tradingPostsToCheck.par.foreach { tradingPost =>
      val (updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked) =
        updatePriceCheckMaps(tradingPost, buyPriceCheckMap, sellPriceCheckMap, startingBase.sold)
      look(updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked, Seq(startingBase, tradingPost), startingBase.distanceTo(tradingPost))
    }
  }

  def updatePriceCheckMaps(tradingPost: TradingPost, buyPriceCheckMap: PriceCheckMap, sellPriceCheckMap: PriceCheckMap, materialsInHull: Set[Material]): (PriceCheckMap, PriceCheckMap, Set[Material], Int) = {
    val updatedBuyPriceCheckMap = buyPriceCheckMap.updated(tradingPost, tradingPost.sold)

    val updatedMaterialsInHull = materialsInHull ++ tradingPost.sold

    val updatedSellPriceCheckMap = sellPriceCheckMap.updated(tradingPost, tradingPost.bought intersect updatedMaterialsInHull)
    val pricesChecked = updatedBuyPriceCheckMap.foldLeft(0)(_ + _._2.size) + updatedSellPriceCheckMap.foldLeft(0)(_ + _._2.size)
    (updatedBuyPriceCheckMap, updatedSellPriceCheckMap, updatedMaterialsInHull, pricesChecked)
  }
}
