package me.amuxix

sealed class Material(val isIllegal: Boolean = false, val maxStock: Option[Int] = None) {
  def prettyPrint: String = s"$this${" " * (Material.longestNameLength - this.toString.length)}"

  val isLegal: Boolean = !isIllegal
}

object Material {
  lazy val materials = Seq(Astatine,   Hydrogen, Fluorine, Iodine, Chlorine, Agricium, Gold, Tungsten, Aluminum, Titanium, AgricultureSupplies, Quartz, Corundum,
    Diamond, Beryl, Laranite, ProcessedFood, Waste, Stims, DistilledSpirits, MedicalSupplies, Scrap, Alutruciatoxine, WiDoW)

  lazy val longestNameLength: Int = materials.map(_.toString.length).max
}

case object Astatine extends Material
case object Hydrogen extends Material
case object Fluorine extends Material
case object Iodine extends Material
case object Chlorine extends Material
case object Agricium extends Material
case object Gold extends Material
case object Tungsten extends Material
case object Aluminum extends Material
case object Titanium extends Material
case object AgricultureSupplies extends Material
case object Quartz extends Material
case object Corundum extends Material
case object Diamond extends Material(maxStock = Some(4000))
case object Beryl extends Material
case object Laranite extends Material
case object ProcessedFood extends Material
case object Waste extends Material
case object Stims extends Material
case object DistilledSpirits extends Material
case object MedicalSupplies extends Material
case object Scrap extends Material
case object Alutruciatoxine extends Material(isIllegal = true, maxStock = Some(5000))
case object WiDoW extends Material(isIllegal = true, maxStock = Some(1000))

