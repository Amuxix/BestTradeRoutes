package me.amuxix

import squants.motion.SpeedOfLight
import squants.motion.VelocityConversions._
import squants.time.TimeConversions._
import squants.{Time, Velocity}

import scala.language.postfixOps

object Ship {
  def fromString(shipName: String): Ship = s"$shipName$$" match {
    case s if s.compareToIgnoreCase(`300i`.getClass.getSimpleName) == 0 => `300i`
    case s if s.compareToIgnoreCase(`315p`.getClass.getSimpleName) == 0 => `315p`
    case s if s.compareToIgnoreCase(`325a`.getClass.getSimpleName) == 0 => `325a`
    case s if s.compareToIgnoreCase(F7CHornet.getClass.getSimpleName) == 0 => F7CHornet
    case s if s.compareToIgnoreCase(MPUVCargo.getClass.getSimpleName) == 0 => MPUVCargo
    case s if s.compareToIgnoreCase(ReliantKore.getClass.getSimpleName) == 0 => ReliantKore
    case s if s.compareToIgnoreCase(AuroraCL.getClass.getSimpleName) == 0 => AuroraCL
    case s if s.compareToIgnoreCase(MustangAlpha.getClass.getSimpleName) == 0 => MustangAlpha
    case s if s.compareToIgnoreCase(AvengerTitan.getClass.getSimpleName) == 0 => AvengerTitan
    case s if s.compareToIgnoreCase(AvengerTitanRenegade.getClass.getSimpleName) == 0 => AvengerTitanRenegade
    case s if s.compareToIgnoreCase(CutlassBlack.getClass.getSimpleName) == 0 => CutlassBlack
    case s if s.compareToIgnoreCase(Freelancer.getClass.getSimpleName) == 0 => Freelancer
    case s if s.compareToIgnoreCase(ConstellationAndromeda.getClass.getSimpleName) == 0 => ConstellationAndromeda
    case s if s.compareToIgnoreCase(ConstellationAquila.getClass.getSimpleName) == 0 => ConstellationAquila
    case s if s.compareToIgnoreCase(Starfarer.getClass.getSimpleName) == 0 => Starfarer
    case s if s.compareToIgnoreCase(StarfarerGemini.getClass.getSimpleName) == 0 => StarfarerGemini
    case s if s.compareToIgnoreCase(Caterpillar.getClass.getSimpleName) == 0 => Caterpillar
    case s if s.compareToIgnoreCase(CaterpillarPirateEdition.getClass.getSimpleName) == 0 => CaterpillarPirateEdition
  }
}

sealed class Ship(val shipCargoSize: Int, val speed: Velocity, size: Size = Medium) {
  val cargoSizeInUnits: Int = shipCargoSize * 100
  val quantumSetupTime: Time = 4 seconds
  val quantumDriveCooldown: Time = 15 seconds
  val quantumDriveSpeed: Velocity = SpeedOfLight / 5
  private def quantumTravelTime(quantumDistance: QuantumDistance): Time =
    quantumDistance.jumps * quantumSetupTime + (quantumDistance.jumps - 1) * QuantumDistance.quantumDriveCooldown + quantumDistance.distance / quantumDriveSpeed
  private def flyingTravelTime(flyingDistance: FlyingDistance): Time = flyingDistance.distance / speed
  def timeToTravel(distance: Distance): Time = quantumTravelTime(distance.quantumDistance) + flyingTravelTime(distance.flyingDistance)
}


case object `300i` extends Ship(2, 1188 mps)
case object `315p` extends Ship(2, 1223 mps)
case object `325a` extends Ship(2, 1314 mps)
case object F7CHornet extends Ship(2, 1224 mps)
case object MPUVCargo extends Ship(2, 918 mps)
case object ReliantKore extends Ship(4, 1151 mps)
case object AuroraCL extends Ship(6, 1093 mps)
case object MustangAlpha extends Ship(6, 1160 mps)
case object AvengerTitan extends Ship(8, 1115 mps)
case object AvengerTitanRenegade extends Ship(8, 1115 mps)
case object CutlassBlack extends Ship(46, 1113 mps)
case object Freelancer extends Ship(66, 1004 mps)
case object ConstellationAndromeda extends Ship(96, 911 mps)
case object ConstellationAquila extends Ship(96, 987 mps)
case object Starfarer extends Ship(295, 888 mps)
case object StarfarerGemini extends Ship(295, 890 mps)
case object Caterpillar extends Ship(576, 892 mps)
case object CaterpillarPirateEdition extends Ship(576, 892 mps)
