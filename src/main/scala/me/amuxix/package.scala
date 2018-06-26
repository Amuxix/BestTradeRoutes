package me

package object amuxix {

  /**
    * This value class represents the atmospheric pressure in relation to earths as a multiplier.
    * So a celestial body with an atmospheric pressure of 2 Earths would have twice the atmospheric pressure
    */
  case class Earths(value: Double) extends AnyVal

  case class G(value: Double) extends AnyVal

  case class Km(value: Int) extends AnyVal {
    def +(other: Km) = Km(value + other.value)
  }

  /*case class AU(value: Int) extends AnyVal {
    //def toKilometers: Long = 1495978707 * 100 * value
  }*/
}
