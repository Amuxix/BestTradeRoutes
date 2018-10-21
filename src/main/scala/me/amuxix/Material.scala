package me.amuxix

sealed class Material(val isIllegal: Boolean = false, val maxSupply: Option[Int] = None, val maxDemand: Option[Int] = None) {
  def prettyPrint: String = s"$this${" " * (Material.longestNameLength - this.toString.length)}"

  val isLegal: Boolean = !isIllegal
}

sealed trait Vice { self: Material =>
  override val maxDemand: Option[Int] = Some(1000)
}
sealed trait Mineral { self: Material => }
sealed trait Metal { self: Material => }
sealed trait Gas { self: Material => }
sealed trait Medicine { self: Material => }
sealed trait Drug { self: Material =>
  override val isLegal: Boolean = false
}



object Material {
  lazy val materials = Seq(Astatine,   Hydrogen, Fluorine, Iodine, Chlorine, Agricium, Gold, Tungsten, Aluminum, Titanium, AgricultureSupplies, Quartz, Corundum,
    Diamond, Beryl, Laranite, ProcessedFood, Waste, Stims, DistilledSpirits, MedicalSupplies, Scrap, Alutruciatoxine, WiDoW)

  lazy val longestNameLength: Int = materials.map(_.toString.length).max
}

case object Astatine extends Material with Gas
case object Hydrogen extends Material with Gas
case object Fluorine extends Material with Gas
case object Iodine extends Material with Gas
case object Chlorine extends Material with Gas
case object Agricium extends Material with Mineral
case object Gold extends Material with Metal
case object Tungsten extends Material with Metal
case object Aluminum extends Material with Metal
case object Titanium extends Material with Metal
case object AgricultureSupplies extends Material
case object Quartz extends Material with Mineral
case object Corundum extends Material with Mineral
case object Diamond extends Material(maxSupply = Some(4000))
case object Beryl extends Material with Mineral
case object Laranite extends Material with Mineral
case object ProcessedFood extends Material
case object Waste extends Material
case object Stims extends Material with Vice
case object DistilledSpirits extends Material with Vice
case object MedicalSupplies extends Material
case object Scrap extends Material
case object Alutruciatoxine extends Material(maxSupply = Some(5000)) with Drug
case object WiDoW extends Material(maxSupply = Some(1000)) with Drug

