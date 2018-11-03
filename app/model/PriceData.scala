package model

import logic.Material
import play.api.data.FormError
import play.api.data.format.Formatter

case class PriceData(material: Material, price: Option[BigDecimal])

object PriceData {
  implicit val materialFormat = new Formatter[Material] {
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], Material] =
      data.get(key)
        .flatMap(Material.findByName)
        .toRight(Seq(FormError(key, "error.materialNotFound", Nil)))

    def unbind(key: String, value: Material) = Map(key -> value.getClass.getSimpleName)
  }
}
