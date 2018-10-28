package me.amuxix

import squants.space.{Kilometers, Length}

case class Point private(x: Long, y: Long, z: Long) {
  def -(o: Point): Point = new Point(x - o.x, y - o.y, z - o.z)
  def linearDistance(o: Point): Length = Kilometers(sqrt(pow(x - o.x) + pow(y - o.y) + pow(z - o.z)))
}

object Point {
  def apply(x: Length, y: Length, z: Length): Point = new Point(x.value.toLong, y.value.toLong, z.value.toLong)
  private def apply(x: Long, y: Long, z: Long): Point = new Point(x, y, z)
}

case class Line(p1: Point, p2: Point) {
  case class LineEquation(a: Long, b: Long, c: Long, d: Long)

  val eq: LineEquation = p2 - p1 match {
    case Point(0, 0, 0) =>
      throw new UnsupportedOperationException("The points must be different.")
    case Point(x, y, z) =>
      var equation = LineEquation(0, 0, 0, 0)
      var d: Long = 1
      if (x == 0) {
        equation = equation.copy(a = 1)
      } else {
        equation = equation.copy(a = x)
        d = d * x
      }

      if (y == 0) {
        equation = equation.copy(b = 1)
      } else {
        equation = equation.copy(b = y)
        d = d * y
      }

      if (z == 0) {
        equation = equation.copy(c = 1)
      } else {
        equation = equation.copy(c = z)
        d = d * z
      }
      equation.copy(d = d)
  }

  def distance(p: Point): Length = {
    Kilometers(math.abs(eq.a * p.x + eq.b * p.y + eq.c * p.z + eq.d) / math.sqrt(pow(eq.a) + pow(eq.b) + pow(eq.c)))
  }

  def pow(a: Long): Long = a * a
}
