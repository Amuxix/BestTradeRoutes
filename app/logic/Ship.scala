package logic

import logic.util.FindByName
import squants.motion.SpeedOfLight
import squants.motion.VelocityConversions._
import squants.time.TimeConversions._
import squants.{Time, Velocity}

import scala.language.postfixOps

object Ship extends FindByName[Ship] {
  override val values: Set[Ship] = Set(
    `300i`,
    `315p`,
    `325a`,
    F7CHornet,
    MPUVCargo,
    ReliantKore,
    AuroraCL,
    MustangAlpha,
    AvengerTitan,
    AvengerTitanRenegade,
    CutlassBlack,
    Freelancer,
    ConstellationAndromeda,
    ConstellationAquila,
    Starfarer,
    StarfarerGemini,
    Caterpillar,
    CaterpillarPirateEdition,
  )
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
