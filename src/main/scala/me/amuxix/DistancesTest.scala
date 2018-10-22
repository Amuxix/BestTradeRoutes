package me.amuxix

import me.amuxix.stanton.StantonSystem
import me.amuxix.stanton.bases._

object DistancesTest {
  def main(args: Array[String]): Unit = {
    println(StantonSystem.center)
    StantonSystem.celestialBodies.foreach(body => println(body.system))
    val base2 = PortOlisar
    val base1 = Levski
    println(base1.celestialBody.system)
    println(base2.celestialBody.system)
    println(base1.celestialBody.system == base2.celestialBody.system)
    val distance = base1.distanceTo(base2)
    println(distance)
    println(distance.timeToTravel(Freelancer))
    println("done")
  }
}
