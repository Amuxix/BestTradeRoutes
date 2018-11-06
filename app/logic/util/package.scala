package logic

package object util {
  def beautify(string: String): String = string.replaceAll("([a-z])([A-Z])", "$1 $2")
}
