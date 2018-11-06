package model

import logic.{Material, TradingPost}
import play.api.data.FormError
import play.api.data.format.Formatter

case class PriceData(material: Material, price: Option[Double], isBuy: Boolean) {
  def toPrice(tradingPost: TradingPost): Option[Price] = price.map(price => Price(tradingPost, material, price, isBuy))
}

object PriceData {
  implicit val materialFormat = new Formatter[Material] {
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], Material] =
      data.get(key)
        .flatMap(Material.findByName)
        .toRight(Seq(FormError(key, s"Could not find ${data(key)}", Nil)))

    def unbind(key: String, value: Material) = Map(key -> value.toString)
  }
}
