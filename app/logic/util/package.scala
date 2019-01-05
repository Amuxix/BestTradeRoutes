package logic

package object util {
  def beautify(string: String): String = string.replaceAll("([a-z])([A-Z0-9])", "$1 $2")

  implicit object NameOrdering extends Ordering[Any] {
    override def compare(x: Any, y: Any): Int = x.toString compare y.toString
  }
}
