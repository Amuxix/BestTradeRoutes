package logic

sealed class Material(val isIllegal: Boolean = false, val maxSupply: Option[Int] = None, val maxDemand: Option[Int] = None) {
  def prettyPrint: String = s"$this${" " * (Material.longestNameLength - this.toString.length)}"

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
  lazy val materials = Seq(Astatine,   Hydrogen, Fluorine, Iodine, Chlorine, Agricium, Gold, Tungsten, Aluminum, Titanium, AgricultureSupplies, Quartz, Corundum,
    Diamond, Beryl, Laranite, ProcessedFood, Waste, Stims, DistilledSpirits, MedicalSupplies, Scrap, Alutruciatoxine, WiDoW)

  lazy val longestNameLength: Int = materials.map(_.toString.length).max
  
  def findByName(materialName: String): Option[Material] = materialName.replaceAll(" ", "") + "$" match {
    case s if s.equalsIgnoreCase(Astatine.getClass.getSimpleName) => Some(Astatine)
    case s if s.equalsIgnoreCase(Hydrogen.getClass.getSimpleName) => Some(Hydrogen)
    case s if s.equalsIgnoreCase(Fluorine.getClass.getSimpleName) => Some(Fluorine)
    case s if s.equalsIgnoreCase(Iodine.getClass.getSimpleName) => Some(Iodine)
    case s if s.equalsIgnoreCase(Chlorine.getClass.getSimpleName) => Some(Chlorine)
    case s if s.equalsIgnoreCase(Agricium.getClass.getSimpleName) => Some(Agricium)
    case s if s.equalsIgnoreCase(Gold.getClass.getSimpleName) => Some(Gold)
    case s if s.equalsIgnoreCase(Tungsten.getClass.getSimpleName) => Some(Tungsten)
    case s if s.equalsIgnoreCase(Aluminum.getClass.getSimpleName) => Some(Aluminum)
    case s if s.equalsIgnoreCase(Titanium.getClass.getSimpleName) => Some(Titanium)
    case s if s.equalsIgnoreCase(AgricultureSupplies.getClass.getSimpleName) => Some(AgricultureSupplies)
    case s if s.equalsIgnoreCase(Quartz.getClass.getSimpleName) => Some(Quartz)
    case s if s.equalsIgnoreCase(Corundum.getClass.getSimpleName) => Some(Corundum)
    case s if s.equalsIgnoreCase(Diamond.getClass.getSimpleName) => Some(Diamond)
    case s if s.equalsIgnoreCase(Beryl.getClass.getSimpleName) => Some(Beryl)
    case s if s.equalsIgnoreCase(Laranite.getClass.getSimpleName) => Some(Laranite)
    case s if s.equalsIgnoreCase(ProcessedFood.getClass.getSimpleName) => Some(ProcessedFood)
    case s if s.equalsIgnoreCase(Waste.getClass.getSimpleName) => Some(Waste)
    case s if s.equalsIgnoreCase(Stims.getClass.getSimpleName) => Some(Stims)
    case s if s.equalsIgnoreCase(DistilledSpirits.getClass.getSimpleName) => Some(DistilledSpirits)
    case s if s.equalsIgnoreCase(MedicalSupplies.getClass.getSimpleName) => Some(MedicalSupplies)
    case s if s.equalsIgnoreCase(Scrap.getClass.getSimpleName) => Some(Scrap)
    case s if s.equalsIgnoreCase(Alutruciatoxine.getClass.getSimpleName) => Some(Alutruciatoxine)
    case s if s.equalsIgnoreCase(WiDoW.getClass.getSimpleName) => Some(WiDoW)
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
case object Diamond extends Material(maxSupply = Some(4000))
case object Beryl extends Mineral
case object Laranite extends Mineral
case object ProcessedFood extends Material
case object Waste extends Material
case object Stims extends Vice
case object DistilledSpirits extends Vice
case object MedicalSupplies extends Material
case object Scrap extends Material
case object Alutruciatoxine extends Material(maxSupply = Some(5000)) with Drug
case object WiDoW extends Material(maxSupply = Some(1000)) with Drug

