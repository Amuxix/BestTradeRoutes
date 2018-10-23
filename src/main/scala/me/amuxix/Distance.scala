package me.amuxix

object Distance {
  val zero = Distance(QuantumDistance.zero, FlyingDistance.zero)
}

case class Distance(quantumDistance: QuantumDistance, flyingDistance: FlyingDistance) {
  def +(other: Distance): Distance = copy(quantumDistance + other.quantumDistance, flyingDistance + other.flyingDistance)
  def timeToTravel(ship: Ship): Long = quantumDistance.timeToTravel(ship) + flyingDistance.timeToTravel(ship)
  override def toString: String = s"$quantumDistance $flyingDistance"
}

object FlyingDistance {
  val zero = FlyingDistance(0)
}

case class FlyingDistance(distance: Km) {
  def +(other: FlyingDistance): FlyingDistance = FlyingDistance(distance + other.distance)
  def timeToTravel(ship: Ship): Long = (distance * 1000) / ship.speed
  override def toString: String = s"FlyingDistance($distance)"
}

object QuantumDistance {
  val quantumDriveCooldown = 15
  val zero = QuantumDistance(0, 0)
}

case class QuantumDistance(jumps: Int, distance: Km) {
  def +(other: QuantumDistance): QuantumDistance = QuantumDistance(jumps + other.jumps, distance + other.distance)
  def timeToTravel(ship: Ship): Long =
    (jumps * (ship.calibrationPlusDriveSpoolTime + QuantumDistance.quantumDriveCooldown) + distance / ship.quantumDriveSpeed).toLong

  override def toString: String = s"QuantumDistance($jumps, $distance)"
}