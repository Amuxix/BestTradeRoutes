package me.amuxix

import me.amuxix.Base.bases
import me.amuxix.stanton.DrugLab

object BestPriceCheckRoute {
  type PriceCheckMap = Map[(Base, Material), Boolean]

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
      bases.flatMap { base =>
        base.buy.map { case (material, _) =>
          (base, material) -> false
        }
      }.toMap
    val emptySellCheckMap: PriceCheckMap =
      bases.flatMap { base =>
        base.sell.map { case (material, _) =>
          (base, material) -> false
        }
      }.toMap

    println(look(emptyBuyCheckMap, emptySellCheckMap))
  }

  def checkBasePrices(base: Base, buyPricesChecked: PriceCheckMap, sellPricesChecked: PriceCheckMap): (PriceCheckMap, PriceCheckMap) = {
    val updatedBuyPrices = buyPricesChecked.map {
      case ((`base`, material), false) if base.buy.contains(material) => (base, material) -> true
      case rest => rest
    }

    val updatedSellPrices = sellPricesChecked.map {
      case ((`base`, material), false) if updatedBuyPrices.exists {
        case ((_, `material`), true) => true
        case _ => false
      } => (base, material) -> true
      case rest => rest
    }
    (updatedBuyPrices, updatedSellPrices)
  }
}
