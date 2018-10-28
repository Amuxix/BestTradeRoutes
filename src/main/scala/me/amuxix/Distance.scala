package me.amuxix

import squants.space.LengthConversions._
import squants.time.TimeConversions._
import squants.{Length, Time}

import scala.language.postfixOps

object Distance {
  val Zero = Distance(QuantumDistance.Zero, FlyingDistance.Zero)
  val MaxValue = Distance(QuantumDistance.MaxValue, FlyingDistance.MaxValue)
}

case class Distance(quantumDistance: QuantumDistance, flyingDistance: FlyingDistance) extends Ordered[Distance] {
  def +(other: Distance): Distance = copy(quantumDistance + other.quantumDistance, flyingDistance + other.flyingDistance)
  def timeToTravel(ship: Ship): Time = quantumDistance.timeToTravel(ship) + flyingDistance.timeToTravel(ship)

  override def compare(that: Distance): Int = timeToTravel(Freelancer).compare(that.timeToTravel(Freelancer))

  override def toString: String = s"$quantumDistance $flyingDistance"
}

object FlyingDistance {
  val Zero = FlyingDistance(0 meters)
  val MaxValue = FlyingDistance(Int.MaxValue meters)
}

case class FlyingDistance(distance: Length) {
  def +(other: FlyingDistance): FlyingDistance = FlyingDistance(distance + other.distance)
  def timeToTravel(ship: Ship): Time = distance / ship.speed
  override def toString: String = s"FlyingDistance($distance)"
}

object QuantumDistance {
  val quantumDriveCooldown: Time = 15 seconds
  val Zero = QuantumDistance(0, 0 meters)
  val MaxValue = QuantumDistance(Int.MaxValue, Int.MaxValue meters)
}

case class QuantumDistance(jumps: Int, distance: Length) {
  def +(other: QuantumDistance): QuantumDistance = QuantumDistance(jumps + other.jumps, distance + other.distance)
  def timeToTravel(ship: Ship): Time =
    jumps * ship.calibrationPlusDriveSpoolTime + (jumps - 1) * QuantumDistance.quantumDriveCooldown + distance / ship.quantumDriveSpeed

  override def toString: String = s"QuantumDistance($jumps, $distance)"
}