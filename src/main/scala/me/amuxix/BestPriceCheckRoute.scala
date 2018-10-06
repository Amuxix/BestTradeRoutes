package me.amuxix

import me.amuxix.Base.bases
import me.amuxix.stanton.DrugLab

import scala.collection.parallel.ParMap

object BestPriceCheckRoute {
  type PriceCheckMap = ParMap[(Base, Material), Boolean]

  def main(args: Array[String]): Unit = {
    val basesToCheck = (bases diff Seq(DrugLab)).par

    def look(buyPriceCheckMap: PriceCheckMap, sellPriceCheckMap: PriceCheckMap, previousBases: Seq[Base] = Seq.empty): Seq[Base] = {
      if (buyPriceCheckMap.forall(_._2) && sellPriceCheckMap.forall(_._2)) previousBases
      else {
        val (nextBase, (updatedBuyPriceCheckMap, updatedSellPriceCheckMap)) = basesToCheck
          .filter(base => previousBases.lastOption.fold(true)(_ != base))
          .map(base => (base, checkBasePrices(base, buyPriceCheckMap, sellPriceCheckMap)))
          .maxBy {
            case (_, (buyPrices, sellPrices)) => buyPrices.count(_._2) + sellPrices.count(_._2)
          }
        look(updatedBuyPriceCheckMap, updatedSellPriceCheckMap, previousBases :+ nextBase)
      }
    }
    val emptyBuyCheckMap: PriceCheckMap =
      basesToCheck.flatMap { base =>
        base.buy.collect { case (material, _) if material.isLegal =>
          (base, material) -> false
        }
      }.toMap
    val emptySellCheckMap: PriceCheckMap =
      basesToCheck.flatMap { base =>
        base.sell.collect { case (material, _) if material.isLegal =>
          (base, material) -> false
        }
      }.toMap

    println(look(emptyBuyCheckMap, emptySellCheckMap))
  }

  def checkBasePrices(base: Base, buyPriceCheckMap: PriceCheckMap, sellPriceCheckMap: PriceCheckMap): (PriceCheckMap, PriceCheckMap) = {
    val updatedBuyPrices = buyPriceCheckMap.map {
      case ((`base`, material), false) if base.buy.contains(material) => (base, material) -> true
      case rest => rest
    }

    val updatedSellPrices = sellPriceCheckMap.map {
      case ((`base`, material), false) if updatedBuyPrices.exists {
        case ((_, `material`), true) => true
        case _ => false
      } => (base, material) -> true
      case rest => rest
    }
    (updatedBuyPrices, updatedSellPrices)
  }
}
