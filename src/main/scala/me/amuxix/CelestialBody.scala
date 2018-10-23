package me.amuxix

import me.amuxix.stanton.StantonSystem

import scala.language.postfixOps

/**
  * This Celestial body, along with at least 3 others charts the system it is in.
  * This body needs to know the distance to every body in the system he is on.
  */
trait Charter { this: CelestialBody =>
  def distances: Map[CelestialBody, Km]
  protected val absolutePosition: Point
  override lazy val position: Point = absolutePosition

  def apply(celestialBody: CelestialBody): Km = distances(celestialBody)

  lazy val Point(x, y, z) = absolutePosition
}

trait Atmosphere {
  val atmosphericPressure: Earths
  val atmosphereHeight: Km
}

trait Orbits {
  def orbits: Option[CelestialBody]

  def orbits(celestialBody: CelestialBody): Boolean = orbits.contains(celestialBody)
}

trait Inhabited {
  def bases: Set[Base]
  lazy val tradingPosts: Set[TradingPost] = bases.collect {
    case tradingPost: TradingPost => tradingPost
  }
}

sealed abstract class CelestialBody {
  def system: System
  def orbitedBy: Set[CelestialBody with Orbits]
  val gravity: G
  val equatorialRadius: Km
  lazy val position: Point = CelestialBody.position(this)

  def isOrbitedBy(celestialBody: CelestialBody with Orbits): Boolean = orbitedBy.contains(celestialBody)

  def heightOfAtmosphere: Km = this match {
    case hasAtmosphere: Atmosphere => hasAtmosphere.atmosphereHeight
    case _ => 0 Km
  }

  def linearDistance(other: CelestialBody): Long = position.linearDistance(other.position)

  def hasDirectPath(other: CelestialBody): Boolean = {
    lazy val line = Line(position, other.position)
    system.celestialBodies
      .filter(body => body != this && body != other)
      .forall { body =>
        line.distance(body.position) >= body.equatorialRadius
      }
  }

  def distanceTo(other: CelestialBody): QuantumDistance = {
    require(system == other.system, "Both bases must be on the same system.")
    if (this == other) {
      QuantumDistance.zero
    } else {
      if (hasDirectPath(other)) {
        QuantumDistance(1, linearDistance(other))
      } else {
        val (_, maybeStation) = system.spaceStations
          .foldLeft((Long.MaxValue, None: Option[SpaceStation])){
            case (old @ (currentMinimum, _), station) if hasDirectPath(station) && station.hasDirectPath(other)=>
              val distance = linearDistance(station) + station.linearDistance(other)
              if (distance < currentMinimum) {
                (distance, Some(station))
              } else {
                old
              }
            case (distance, _) => distance
          }
        maybeStation.fold {
          //There is no space station that can be used as a midway jump, try more jumps.
          //Start with the station closest to the destination.
          val closestToTarget = system.spaceStations
            .filter(_.hasDirectPath(other))
            .minBy(_.linearDistance(other))
          //When this eventually fails wrap closest target in a Try and improve the logic to find a 3rd or more jump
          //Jump from closestToTarget to other will not be direct!
          distanceTo(closestToTarget) + closestToTarget.distanceTo(other)
        } { station =>
          distanceTo(station) + station.distanceTo(other)
        }
      }
    }
  }

  def prettyPrint: String = s"$this${" " * (CelestialBody.longestNameLength - this.toString.length)}"
}

abstract class Star extends CelestialBody {
  override val gravity: G = 0 G
}

abstract class Planet extends CelestialBody with Orbits

sealed abstract class Satellite extends CelestialBody with Orbits

abstract class Moon extends Satellite

abstract class SpaceStation extends Satellite {
  override val orbitedBy: Set[CelestialBody with Orbits] = Set.empty
  override val gravity: G = 0 G
  override val equatorialRadius: Km = 0 Km
}

object CelestialBody {
  lazy val celestialBodies: Set[CelestialBody] = StantonSystem.celestialBodies
  lazy val longestNameLength: Int = celestialBodies.map(_.toString.length).max

  /**
    * Calculates the position of a given body using the system charters.
    * @param c Body to calculate the position of.
    * @return A point that represents the position of the given body.
    */
  def position(c: CelestialBody): Point = {
    val system = c.system
    //https://www.ece.lsu.edu/scalzo/FDR_Final.pdf
    //https://en.wikipedia.org/wiki/Cramer%27s_rule#Explicit_formulas_for_small_systems
    /**
      * Generic formula to generate a linear equation used to apply the Cramer’s rule
      */
    def e(a: Charter, b: Charter): Array[Long] = Array[Long](
      2 * (b.x - a.x),
      2 * (b.y - a.y),
      2 * (b.z - a.z),
      (pow(a(c)) - pow(b(c))) - (pow(a.x) - pow(b.x)) - (pow(a.y) - pow(b.y)) - (pow(a.z) - pow(b.z))
    )

    val e1: Array[Long] = e(system.c1, system.c2)
    val e2: Array[Long] = e(system.c1, system.c3)
    val e3: Array[Long] = e(system.c1, system.c4)

    /**
      * Function that calculates the determinant of the top matrix used by the Cramer’s rule.
      * This uses the indexes of the columns to calculate it.
      */
    def matrixDeterminant(a: Int = 0, b: Int = 1, c: Int = 2) = new Matrix3(Array(
      e1(a), e1(b), e1(c),
      e2(a), e2(b), e2(c),
      e3(a), e3(b), e3(c),
    )).determinant

    val bottomMatrix = matrixDeterminant()
    val x = matrixDeterminant(a = 3) / bottomMatrix
    val y = matrixDeterminant(b = 3) / bottomMatrix
    val z = matrixDeterminant(c = 3) / bottomMatrix

    Point(x.toInt, y.toInt, z.toInt)
  }
}