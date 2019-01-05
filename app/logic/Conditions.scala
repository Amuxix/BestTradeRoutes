package logic

import logic.stanton.bases._

object Conditions {
  def materialFilter(material: Material): Boolean = material.isLegal

  def tradePostFilter(tradingPost: TradingPost): Boolean = tradingPost match {
    case Jumptown | Levski => false
    case _ => true
  }
}
