package logic

package object util {
  def beautify(string: String): String = string.replaceAll("([a-z])([A-Z0-9])", "$1 $2")
}
