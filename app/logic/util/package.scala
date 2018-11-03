package logic

package object util {
  def beautify(string: String): String = string.dropRight(1).replaceAll("([a-z])([A-Z])", "$1 $2")
}
