package logic

import logic.stanton.bases._

object Conditions {
  def materialFilter(material: Material): Boolean = material.isLegal

  def tradePostFilter(tradingPost: TradingPost): Boolean = tradingPost match {
    case DrugLab | Levski => false
    case _ => true
  }
}
