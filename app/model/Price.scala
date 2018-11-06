package model

import logic.{Material, TradingPost}

case class Price(
  tradingPost: TradingPost,
  material: Material,
  price: Double,
  isBuy: Boolean
) {
  def toPriceData: PriceData = PriceData(
    material,
    Some(price),
    isBuy
  )
}