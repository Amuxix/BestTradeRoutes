package me.amuxix

object Ship {
  def fromString(shipName: String): Ship = shipName.toLowerCase match {
    case `300i`.getClass.getSimpleName.toLowerCase => `300i`
    case `315p`.getClass.getSimpleName.toLowerCase => `315p`
    case `325a`.getClass.getSimpleName.toLowerCase => `325a`
    case F7CHornet.getClass.getSimpleName.toLowerCase => F7CHornet
    case MPUVCargo.getClass.getSimpleName.toLowerCase => MPUVCargo
    case ReliantKore.getClass.getSimpleName.toLowerCase => ReliantKore
    case AuroraCL.getClass.getSimpleName.toLowerCase => AuroraCL
    case MustangAlpha.getClass.getSimpleName.toLowerCase => MustangAlpha
    case AvengerTitan.getClass.getSimpleName.toLowerCase => AvengerTitan
    case AvengerTitanRenegade.getClass.getSimpleName.toLowerCase => AvengerTitanRenegade
    case CutlassBlack.getClass.getSimpleName.toLowerCase => CutlassBlack
    case Freelancer.getClass.getSimpleName.toLowerCase => Freelancer
    case ConstellationAndromeda.getClass.getSimpleName.toLowerCase => ConstellationAndromeda
    case ConstellationAquila.getClass.getSimpleName.toLowerCase => ConstellationAquila
    case Starfarer.getClass.getSimpleName.toLowerCase => Starfarer
    case StarfarerGemini.getClass.getSimpleName.toLowerCase => StarfarerGemini
    case Caterpillar.getClass.getSimpleName.toLowerCase => Caterpillar
    case CaterpillarPirateEdition.getClass.getSimpleName.toLowerCase => CaterpillarPirateEdition
  }
}

sealed class Ship(shipCargoSize: Int, speed: Int) {
  val cargoSizeInUnits: Int = shipCargoSize * 100
}


case object `300i` extends Ship(2, 1188)
case object `315p` extends Ship(2, 1223)
case object `325a` extends Ship(2, 1314)
case object F7CHornet extends Ship(2, 1224)
case object MPUVCargo extends Ship(2, 918)
case object ReliantKore extends Ship(4, 1151)
case object AuroraCL extends Ship(6, 1093)
case object MustangAlpha extends Ship(6, 1160)
case object AvengerTitan extends Ship(8, 1115)
case object AvengerTitanRenegade extends Ship(8, 1115)
case object CutlassBlack extends Ship(46, 1113)
case object Freelancer extends Ship(66, 1004)
case object ConstellationAndromeda extends Ship(96, 911)
case object ConstellationAquila extends Ship(96, 987)
case object Starfarer extends Ship(295, 888)
case object StarfarerGemini extends Ship(295, 890)
case object Caterpillar extends Ship(576, 892)
case object CaterpillarPirateEdition extends Ship(576, 892)
