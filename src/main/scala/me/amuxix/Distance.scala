package me.amuxix

case class Distance(jumps: Int, quantumDistance: Km, distance: Km) {
  def +(other: Distance) = copy(jumps + other.jumps, quantumDistance + other.quantumDistance, distance + other.distance)

  def timeToTravel(ship: Ship): Int =
    (jumps * ship.calibrationPlusDriveSpoolTime + quantumDistance.value / ship.quantumDriveSpeed + distance.value * ship.speed).toInt
}

object Distance {
  val lightSpeed = 3e8
  val quantumDriveSpeed = .2 * 3e8
}
