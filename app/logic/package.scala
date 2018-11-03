package object logic {

  /**
    * This value class represents the atmospheric pressure in relation to earths as a multiplier.
    * So a celestial body with an atmospheric pressure of 2 Earths would have twice the atmospheric pressure
    */
  implicit class Earths(val value: Double) extends AnyVal {
    def Earths: Earths = new Earths(value)
  }

  implicit class G(val value: Double) extends AnyVal {
    def G: G = new G(value)
  }

  implicit class UEC(val value: Int) extends AnyVal with Ordered[UEC] {
    def +(other: UEC): UEC = new UEC(value + other.value)
    def *(other: Double): UEC = new UEC((value * other).toInt)
    def max(other: UEC): UEC = new UEC(value max other.value)
    def UEC: UEC = new UEC(value)

    override def compare(that: UEC): Int = value.compare(that.value)

    override def toString: String = value.toString
  }

  implicit class ExtendedDouble(x: Double) {
    def truncated(n: Int): Double = {
      def p10(n: Int, pow: Long = 10): Long = if (n == 1) pow else p10(n - 1, pow * 10)

      if (n < 0) {
        val m = p10(-n).toDouble
        math.round(x / m) * m
      } else {
        val m = p10(n).toDouble
        math.round(x * m) / m
      }
    }

    def toPercent: String = {
      s"${(x * 100).truncated(2)}%"
    }
  }

  def pow(x: Long): Long = x * x
  def sqrt(x: Long): Long = math.sqrt(x).toLong
}
