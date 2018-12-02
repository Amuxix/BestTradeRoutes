import logic.util.FindByName
import logic.{Material, TradingPost}
import play.api.data.FormError
import play.api.data.format.Formatter

package object model {
  private def findableFormat[T](companion: FindByName[T]) = new Formatter[T] {
    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], T] = {
      data.get(key)
        .flatMap(companion.findByName)
        .toRight(Seq(FormError(key, s"Could not find ${data(key)}", Nil)))
    }

    override def unbind(key: String, value: T): Map[String, String] = Map(key -> value.toString)
  }

  implicit val materialFormat = findableFormat(Material)

  implicit val tradingPostFormat = findableFormat(TradingPost)
}
