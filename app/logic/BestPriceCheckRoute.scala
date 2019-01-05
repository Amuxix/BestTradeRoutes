package logic

import scala.language.postfixOps

case class BestPriceCheckRoute(
  startingBase: TradingPost,
  ignoredMaterials: Set[Material],
  basesIgnored: Set[TradingPost],
  materialsInHull: Set[Material],
  knownBases: Seq[TradingPost],
) {
  type PriceCheckMap = Map[TradingPost, Set[Material]]

  //val tradingPostsToCheck: Seq[TradingPost] = filteredTradingPosts.toSeq.sortBy(tradingPost => (tradingPost.sells.size, -tradingPost.buys.size))
  val tradingPostsToCheck: Seq[TradingPost] = (Universe.tradingPosts -- basesIgnored).toSeq
    .sortBy(tradingPost => (tradingPost.sells.size, -tradingPost.buys.size))

  val totalPrices: Int = tradingPostsToCheck.foldLeft(0)((prices, tradingPost) =>
    prices + (tradingPost.buys -- ignoredMaterials).size + (tradingPost.sells -- ignoredMaterials).size
  )

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
      base -> base.buys
    }.toMap
    val sellPriceCheckMap: PriceCheckMap = knownBases.map { base =>
      base -> base.sells
    }.toMap
    tradingPostsToCheck.par.foreach { tradingPost =>
      val (updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked) =
        updatePriceCheckMaps(tradingPost, buyPriceCheckMap, sellPriceCheckMap, (materialsInHull ++ startingBase.buys) -- ignoredMaterials)
      look(updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked, Seq(startingBase, tradingPost), startingBase.distanceTo(tradingPost))
    }
  }

  def updatePriceCheckMaps(tradingPost: TradingPost, buyPriceCheckMap: PriceCheckMap, sellPriceCheckMap: PriceCheckMap, materialsInHull: Set[Material]): (PriceCheckMap, PriceCheckMap, Set[Material], Int) = {
    val updatedBuyPriceCheckMap = buyPriceCheckMap.updated(tradingPost, tradingPost.buys -- ignoredMaterials)

    val updatedMaterialsInHull = materialsInHull ++ (tradingPost.buys -- ignoredMaterials)

    val updatedSellPriceCheckMap = sellPriceCheckMap.updated(tradingPost, (tradingPost.sells -- ignoredMaterials) intersect updatedMaterialsInHull)
    val pricesChecked = updatedBuyPriceCheckMap.foldLeft(0)(_ + _._2.size) + updatedSellPriceCheckMap.foldLeft(0)(_ + _._2.size)
    (updatedBuyPriceCheckMap, updatedSellPriceCheckMap, updatedMaterialsInHull, pricesChecked)
  }
}
