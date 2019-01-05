package model

import logic.{Material, TradingPost}
import play.api.data.FormError
import play.api.data.format.Formatter

case class PriceData(material: Material, price: Option[Double], isBuy: Boolean) {
  def toPrice(tradingPost: TradingPost): Option[Price] = price.map(price => Price(tradingPost, material, price, isBuy))
}