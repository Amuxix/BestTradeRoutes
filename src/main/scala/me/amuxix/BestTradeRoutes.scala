package me.amuxix

import me.amuxix.stanton.Bases._

import scala.annotation.tailrec
import scala.collection.mutable

object BestTradeRoutes {

  implicit class ExtendedDouble(x: Double) {
    def truncated(n: Int): Double = {
      def p10(n: Int, pow: Long = 10): Long = if (n == 1) pow else p10(n - 1, pow * 10)

      if (n < 0) {
        val m = p10(-n).toDouble
        math.round(x / m) * m
      } else {
        val m = p10(n).toDouble
        math.round(x * m) / m
      }
    }

    def toPercent: String = {
      s"${(x * 100).truncated(2)}%"
    }
  }

  val bases: Seq[Base] = Seq(PortOlisar, ArcCorpMiningArea141, BountyfulHarvestHydroponics, ShubinMiningFacility, KudreOre, TerraMillsHydroFarm, GaletteFamilyFarms, HickesResearchOutpost, TramMyersMining, GrimHex, ArcCorpMiningArea157, BensonMiningOutpost, DeakingReaserchOutpost, DrugLab, Levski)

  def main(args: Array[String]): Unit = {
    val ship = Ship.fromString(args.head)
    val initialInvestment = args(1).toInt
    val startingBase = PortOlisar
    //calculateRoute(bases, freelancer, initialInvestment)
    var investment = initialInvestment
    var base: Base = startingBase
    println(s"Starting at $base")
    while (true) {
      val (nextBase, material, profit) = calculateNextBestJump(base, bases, ship, investment)
      nextBase match {
        case nextBase: LandBase =>
          println(s"$nextBase:${nextBase.celestialBody}(${nextBase.closestOrbitalMarker}) -> $material: $profit")
        case nextBase: SpaceStation =>
          println(s"$nextBase:${base.celestialBody} -> $material: $profit")
      }
      base = nextBase
      investment += profit
      println(s"$material($profit) -> $nextBase")
      scala.io.StdIn.readLine()
    }
  }

  /*def calculateNextBestJump(startingBase: Base, bases: Seq[Base], ship: Ship, initialInvestment: Int, lookahead: Int, allowIllegal: Boolean = false): Seq[(Base, Material, Int)] = {
    @tailrec def inner(startBase: Base, remainingBases: Seq[Base], investment: Int, jumps: Seq[(Base, Material, Int)] = Seq.empty): Seq[(Base, Material, Int)] = {
      if (jumps.length == lookahead) {
        return jumps
      }
      remainingBases match {
        case Seq() => jumps
        case nextBase :: remainingBases => startBase.bestProfit(nextBase, ship, investment, allowIllegal) match {
          case None => jumps
          case Some((material, profit)) => inner(nextBase, remainingBases.tail, investment + profit, (nextBase, material, profit) +: jumps)
        }
      }
    }

    val possibleBases = bases diff Seq(startingBase)
    possibleBases.permutations.toStream.par.map { combinationBases =>
      //println(s"${combinationBases.head} -> ${combinationBases.tail}")
      val route = inner(combinationBases.head, combinationBases.tail, initialInvestment)
      //println(route)
      route
    } maxBy (_.foldLeft(0)(_ + _._3))
  }*/

  def calculateNextBestJump(startingBase: Base, bases: Seq[Base], ship: Ship, startingInvestment: Int, allowIllegal: Boolean = false): (Base, Material, Int) = {
    bases
      .collect { case target if startingBase.canTrade(target) =>
        startingBase
          .bestProfit(target, ship, startingInvestment, allowIllegal)
          .map{ case (material, profit) =>
            (target, material, profit)
          }
      }
      .flatten
      .maxBy(_._3)
  }

  /*def calculateRoute(bases: Seq[Base], ship: Ship, initialInvestment: Int, allowIllegal: Boolean = false): Unit = {
    def inner(startingBase: Base, currentBase: Base, remainingBases: Seq[Base], investment: Int, jumps: Seq[(Base, Material, Double)]): Seq[(Seq[(Base, Material, Double)], Double)] = {
      remainingBases.collect {
        case nextBase if nextBase != currentBase =>
          currentBase.bestProfit(nextBase, ship, investment, allowIllegal) match {
            case Some((material, profit)) =>
              val allJumps: Seq[(Base, Material, Double)] = jumps :+ (currentBase, material, profit.toDouble / investment)
              if (nextBase == startingBase) {
                Seq((allJumps, allJumps.map(_._3).sum / allJumps.length))
              } else {
                inner(startingBase, nextBase, remainingBases diff Seq(nextBase), investment + profit, allJumps)
              }
            case None => Seq.empty
          }
      }.flatten
    }
    /*val folder = new java.io.File(".").getCanonicalPath
    val file = new PrintWriter(new File(folder + File.separatorChar + "results.txt"))*/
    val (jumps, totalProfit) = bases.par.flatMap { base =>
      inner(base, base, bases, initialInvestment, Seq.empty)
    }.maxBy(_._2)
    println(totalProfit.toPercent)
    jumps.foreach { case (base, material, profit) =>
      println(s"$base -> $material(${profit.toPercent})")
    }
    println(s"Expected returns: ${initialInvestment * (jumps.foldLeft(1.toDouble)((acc, x) => acc * (x._3 + 1)) - 1)}")
    //file.close()
  }*/
  /*def possibleTargets(base: Base, possibleBases: Seq[Base])(ship: Ship, investment: Int): Seq[Base] = {
    possibleBases.filter(base.bestProfit(_, ship, investment).isDefined)
  }

  def AStar(start: Base, goal: Base)(ship: Ship, investment: Int) = {
    // The set of nodes already evaluated
    var closedSet = Set.empty[Base]

    // The set of currently discovered nodes that are not evaluated yet.
    // Initially, only the start node is known.
    var openSet: Set[Base] = Set(start)

    // For each node, which node it can most efficiently be reached from.
    // If a node can be reached from many nodes, cameFrom will eventually contain the
    // most efficient previous step.
    var cameFrom = Map.empty[Base, Base]

    // For each node, the cost of getting from the start node to that node.
    var gScore = Map.empty[Base, Int].withDefaultValue(Int.MaxValue)

    // The cost of going from start to start is zero.
    gScore += (start -> 0)

    // For each node, the total cost of getting from the start node to the goal
    // by passing by that node. That value is partly known, partly heuristic.
    var fScore = Map.empty[Base, Int].withDefaultValue(Int.MaxValue)

    // For the first node, that value is completely heuristic.
    fScore += (start -> heuristic_cost_estimate(start, goal))

    while (openSet.nonEmpty) {
      val current = openSet.minBy(fScore(_))
      if (current == goal) {
        return reconstructPath(cameFrom, current)
      } else {
        openSet += current
        closedSet += current
        for (neighbor <- possibleTargets(current, bases)(ship, investment)) {
          if ((closedSet contains neighbor) == false) {
            if ((openSet contains neighbor) == false) {
              openSet += neighbor // Discover a new node
            }
            // The distance from start to a neighbor
            //the "dist_between" function may vary as per the solution requirements.
            val tentative_gScore = gScore(current) + current.bestProfit(neighbor, ship, investment).fold(Int.MaxValue)(_._2)
            if (tentative_gScore < gScore(neighbor)) {
              // This path is the best until now. Record it!
              cameFrom += (neighbor -> current)
              gScore += (neighbor -> tentative_gScore)
              fScore += (neighbor -> (gScore(neighbor) + heuristic_cost_estimate(neighbor, goal)))
            }
          }
        }
      }
    }
    failure
  }

  def reconstructPath(cameFrom: Map[Base, Base], current: Base): Set[Base] = {
    val a = Set.empty[Int]
     cameFrom.keySet.map(cameFrom) + current
  }*/
}