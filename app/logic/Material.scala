package logic

import logic.util.beautify

sealed class Material(val isIllegal: Boolean = false, val maxSupply: Option[Int] = None, val maxDemand: Option[Int] = None) {
  def prettyPrint: String = s"$this${" " * (Material.longestNameLength - this.toString.length)}"
  def displayName: String = beautify(toString)

  val isLegal: Boolean = !isIllegal
}

sealed trait Vice extends Material {
  override val maxDemand: Option[Int] = Some(1000)
}
sealed trait Mineral extends Material
sealed trait Metal extends Material
sealed trait Gas extends Material
sealed trait Medicine extends Material
sealed trait Drug extends Material {
  override val isLegal: Boolean = false
}



object Material {
  lazy val materials = Seq(Astatine, Hydrogen, Fluorine, Iodine, Chlorine, Agricium, Gold, Tungsten, Aluminum, Titanium, AgricultureSupplies, Quartz, Corundum,
    Diamond, Beryl, Laranite, ProcessedFood, Waste, Stims, DistilledSpirits, MedicalSupplies, Scrap, Alutruciatoxine, WiDoW)

  lazy val longestNameLength: Int = materials.map(_.toString.length).max
  
  def findByName(materialName: String): Option[Material] = materialName match {
    case s if s.equalsIgnoreCase(Astatine.toString) => Some(Astatine)
    case s if s.equalsIgnoreCase(Hydrogen.toString) => Some(Hydrogen)
    case s if s.equalsIgnoreCase(Fluorine.toString) => Some(Fluorine)
    case s if s.equalsIgnoreCase(Iodine.toString) => Some(Iodine)
    case s if s.equalsIgnoreCase(Chlorine.toString) => Some(Chlorine)
    case s if s.equalsIgnoreCase(Agricium.toString) => Some(Agricium)
    case s if s.equalsIgnoreCase(Gold.toString) => Some(Gold)
    case s if s.equalsIgnoreCase(Tungsten.toString) => Some(Tungsten)
    case s if s.equalsIgnoreCase(Aluminum.toString) => Some(Aluminum)
    case s if s.equalsIgnoreCase(Titanium.toString) => Some(Titanium)
    case s if s.equalsIgnoreCase(AgricultureSupplies.toString) => Some(AgricultureSupplies)
    case s if s.equalsIgnoreCase(Quartz.toString) => Some(Quartz)
    case s if s.equalsIgnoreCase(Corundum.toString) => Some(Corundum)
    case s if s.equalsIgnoreCase(Diamond.toString) => Some(Diamond)
    case s if s.equalsIgnoreCase(Beryl.toString) => Some(Beryl)
    case s if s.equalsIgnoreCase(Laranite.toString) => Some(Laranite)
    case s if s.equalsIgnoreCase(ProcessedFood.toString) => Some(ProcessedFood)
    case s if s.equalsIgnoreCase(Waste.toString) => Some(Waste)
    case s if s.equalsIgnoreCase(Stims.toString) => Some(Stims)
    case s if s.equalsIgnoreCase(DistilledSpirits.toString) => Some(DistilledSpirits)
    case s if s.equalsIgnoreCase(MedicalSupplies.toString) => Some(MedicalSupplies)
    case s if s.equalsIgnoreCase(Scrap.toString) => Some(Scrap)
    case s if s.equalsIgnoreCase(Alutruciatoxine.toString) => Some(Alutruciatoxine)
    case s if s.equalsIgnoreCase(WiDoW.toString) => Some(WiDoW)
    case _ => None
  }
}

case object Astatine extends Gas
case object Hydrogen extends Gas
case object Fluorine extends Gas
case object Iodine extends Gas
case object Chlorine extends Gas
case object Agricium extends Mineral
case object Gold extends Metal
case object Tungsten extends Metal
case object Aluminum extends Metal
case object Titanium extends Metal
case object AgricultureSupplies extends Material
case object Quartz extends Mineral
case object Corundum extends Mineral
case object Diamond extends Material(maxSupply = Some(4000)) with Mineral
case object Beryl extends Mineral
case object Laranite extends Mineral
case object ProcessedFood extends Material
case object Waste extends Material
case object Stims extends Vice
case object DistilledSpirits extends Vice
case object MedicalSupplies extends Material
case object Scrap extends Material
case object Alutruciatoxine extends Material(maxSupply = Some(5000)) with Drug
case object WiDoW extends Material(maxSupply = Some(1000)) with Drug {
  override val displayName: String = "WiDoW"
}

