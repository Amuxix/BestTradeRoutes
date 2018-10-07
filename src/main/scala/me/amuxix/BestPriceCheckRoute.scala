package me.amuxix

import me.amuxix.Base.bases
import me.amuxix.stanton.DrugLab

object BestPriceCheckRoute {
  type PriceCheckMap = Map[Base, Set[Material]]

  def materialFilter(material: Material): Boolean = material.isLegal

  def main(args: Array[String]): Unit = {
    val basesToCheck = bases diff Seq(DrugLab)
    var bestRouteSize = Int.MaxValue

    val totalPrices = basesToCheck.foldLeft(0)((prices, base) =>
      prices + base.sold.size + base.bought.size
    )

    def look(buyPriceCheckMap: PriceCheckMap, sellPriceCheckMap: PriceCheckMap, materialsInHull: Set[Material], pricesChecked: Int, previousBases: Seq[Base]): Unit = {
      if (pricesChecked == totalPrices) {
        bestRouteSize = previousBases.size
        println(s"$bestRouteSize -> ${previousBases.mkString(", ")}")
      } else if (previousBases.size < bestRouteSize - 1) {
        // If the number of bases visited is bestRouteSize - 1 and we still need to check more prices this solution will not be better than one we already have.
        basesToCheck.foreach { base =>
          val (updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked) = updatePriceCheckMaps(base, buyPriceCheckMap, sellPriceCheckMap, materialsInHull)
          if (updatedPricesChecked > pricesChecked) { //Discard bases that give no new prices.
            look(updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked, previousBases :+ base)
          }
        }
      }
    }
    basesToCheck.par.foreach { base =>
      val (updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked) = updatePriceCheckMaps(base, Map.empty, Map.empty, Set.empty)
      look(updatedBuyPrices, updatedSellPrices, updatedMaterialsInHull, updatedPricesChecked, Seq(base))
    }
  }

  def updatePriceCheckMaps(base: Base, buyPriceCheckMap: PriceCheckMap, sellPriceCheckMap: PriceCheckMap, materialsInHull: Set[Material]): (PriceCheckMap, PriceCheckMap, Set[Material], Int) = {
    val updatedBuyPriceCheckMap = buyPriceCheckMap.updated(base, base.sold)

    val updatedMaterialsInHull = materialsInHull ++ base.sold

    val updatedSellPriceCheckMap = sellPriceCheckMap.updated(base, base.bought intersect updatedMaterialsInHull)
    val pricesChecked = updatedBuyPriceCheckMap.foldLeft(0)(_ + _._2.size) + updatedSellPriceCheckMap.foldLeft(0)(_ + _._2.size)
    (updatedBuyPriceCheckMap, updatedSellPriceCheckMap, updatedMaterialsInHull, pricesChecked)
  }
}
