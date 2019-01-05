package logic.util

trait FindByName[T] {
  val values: Set[T]

  private lazy val map = values.map { value =>
    value.toString.toUpperCase -> value
  }.toMap

  def findByName(name: String): Option[T] = map.get(name.toUpperCase)
}
