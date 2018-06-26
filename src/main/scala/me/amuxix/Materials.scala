package me.amuxix

sealed class Material(val isIllegal: Boolean = false, val maxStock: Option[Int] = None) {
  def prettyPrint: String = s"$this${" " * (AggricultureSupplies.toString.length - this.toString.length)}"
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
case object AggricultureSupplies extends Material
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
case object Altruciatoxin extends Material(isIllegal = true, maxStock = Some(5000))
case object WiDoW extends Material(isIllegal = true, maxStock = Some(1000))

