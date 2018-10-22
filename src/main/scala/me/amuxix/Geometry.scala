package me.amuxix

case class Point(x: Int, y: Int, z: Int) {
  def -(o: Point): Point = Point(x - o.x, y - o.y, z - o.z)
  def linearDistance(o: Point): Int = sqrt(pow(x - o.x) + pow(y - o.y) + pow(z - o.z))
}

case class Line(p1: Point, p2: Point) {
  case class LineEquation(a: Int, b: Int, c: Int, d: Int)

  val eq: LineEquation = p2 - p1 match {
    case Point(0, 0, 0) =>
      throw new UnsupportedOperationException("The points must be different.")
    case Point(x, y, z) =>
      var equation = LineEquation(0, 0, 0, 0)
      var d = 1
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

  def distance(p: Point): Int = {
    (math.abs(eq.a * p.x + eq.b * p.y + eq.c * p.z + eq.d) / math.sqrt(pow(eq.a) + pow(eq.b) + pow(eq.c))).toInt
  }

  def pow(a: Int): Int = a * a
}
