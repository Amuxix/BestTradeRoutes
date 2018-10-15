package me.amuxix

import me.amuxix.BestTradeRoutes.{Trade, allowIllegal, profitToTradingPosts}

object Base {
  val tradingPosts: Set[TradingPost] = stanton.tradingPosts
  val longestNameLength: Int = tradingPosts.map(_.toString.length).max
}

abstract class Base {
  val celestialBody: CelestialBody

  def distanceFromOrbit: Km = {
    this match {
      case _: OnLand => 50 Km
      case _ => 10 Km
    }
  }

  lazy val ancestors: Seq[CelestialBody] = celestialBody.ancestors

  def distanceTo(other: Base): Km =
    if (this == other) {
      0
    } else {
      if (celestialBody == other.celestialBody) {
        celestialBody.heightOfAtmosphere + other.distanceFromOrbit
      } else if (celestialBody.orbits.contains(other.celestialBody)) {
        // The celestial body where this base is located orbits the celestial body where the other base is located
        celestialBody.heightOfAtmosphere + other.distanceFromOrbit + 1000
      } else if (other.celestialBody.orbits.contains(celestialBody)) {
        // The celestial body where the other base is located orbits the celestial body where this base is located
        other.celestialBody.heightOfAtmosphere + distanceFromOrbit + 1000
      } else {
        (for {
          lowestCommonAncestor <- ancestors.find(other.ancestors.contains)
          lowestCommonAncestorChildren = lowestCommonAncestor.orbitedBy
          nearestAncestorOfThis <- ancestors.find(lowestCommonAncestorChildren.contains)
          nearestAncestorOfOther <- other.ancestors.find(lowestCommonAncestorChildren.contains)
        } yield lowestCommonAncestor.distance(nearestAncestorOfThis, nearestAncestorOfOther))
          .getOrElse(throw new Exception(s"Cannot calculate distance from $this to $other"))
      }
    }

  def print: String = s"$this($celestialBody)"

  def prettyPrint: String = s"$this${" " * (Base.longestNameLength - this.toString.length)}"

  def prettyPrintNextJump(nextBase: Base): String = {
    nextBase match {
      case _ if nextBase.celestialBody.isInstanceOf[SpaceStation] =>
        s"${nextBase.prettyPrint}${" " * (CelestialBody.longestNameLength + 3)}"
      case _ =>
        s"${nextBase.prettyPrint} @ ${nextBase.celestialBody.prettyPrint}"
    }
  }
}

abstract class TradingPost extends Base {
  val buy: Map[Material, Double] //Materials you can buy at this base
  lazy val sold: Set[Material] = buy.keySet.filter(BestPriceCheckRoute.materialFilter)
  val sell: Map[Material, Double] //materials you can sell at this base
  lazy val bought: Set[Material] = sell.keySet.filter(BestPriceCheckRoute.materialFilter)

  def canTrade(other: TradingPost): Boolean = {
    this != other && buy.exists { case (material, _) =>
      (allowIllegal || !material.isIllegal) && other.sell.contains(material)
    }
  }

  def bestProfit(other: TradingPost, ship: Ship, investment: UEC): Option[Trade] = {
    profitToTradingPosts(this, other).collect {
      case (material, Some(profit)) =>
        val cost = this.buy(material)
        val moneyBuys: Int = (investment.value / cost).toInt min ship.cargoSizeInUnits
        val maxStock: Int = material.maxStock.fold(moneyBuys)(_ min moneyBuys)
        (material,  UEC((maxStock * profit).toInt), maxStock)
    } match {
      case Seq() => None
      case nonEmpty: Seq[Trade] => Some(nonEmpty.maxBy(_._2))
    }
  }
}

trait OnLand