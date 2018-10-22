package me.amuxix

object Distance {
  val zero = Distance(QuantumDistance.zero, FlyingDistance.zero)
}

case class Distance(quantumDistance: QuantumDistance, flyingDistance: FlyingDistance) {
  def +(other: Distance): Distance = copy(quantumDistance + other.quantumDistance, flyingDistance + other.flyingDistance)
  def timeToTravel(ship: Ship): Int = quantumDistance.timeToTravel(ship) + flyingDistance.timeToTravel(ship)
}

object FlyingDistance {
  val zero = FlyingDistance(0)
}

case class FlyingDistance(distance: Km) {
  def +(other: FlyingDistance): FlyingDistance = FlyingDistance(distance + other.distance)
  def timeToTravel(ship: Ship): Int = distance * ship.speed
}

object QuantumDistance {
  val quantumDriveCooldown = 15
  val zero = QuantumDistance(0, 0)
}

case class QuantumDistance(jumps: Int, distance: Km) {
  def +(other: QuantumDistance): QuantumDistance = QuantumDistance(jumps + other.jumps, distance + other.distance)
  def timeToTravel(ship: Ship): Int =
    (jumps * (ship.calibrationPlusDriveSpoolTime + QuantumDistance.quantumDriveCooldown) + distance / ship.quantumDriveSpeed).toInt
}