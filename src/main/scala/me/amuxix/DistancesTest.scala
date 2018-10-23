package me.amuxix

import me.amuxix.stanton.bases._

object DistancesTest {
  def main(args: Array[String]): Unit = {
    val base1 = ArcCorpMiningArea157
    val base2 = ArcCorpMiningArea141
    println(base1.celestialBody.position)
    println(base2.celestialBody.position)
    val distance = base1.distanceTo(base2)
    println(distance)
    println(distance.timeToTravel(Freelancer))
  }
}
