package logic

class Matrix3(val m: Array[Long] = Array.ofDim[Long](9)) {
  def determinant: Long = m(0) * m(4) * m(8) + m(1) * m(5) * m(6) + m(2) * m(3) * m(7) - m(2) * m(4) * m(6) - m(1) * m(3) * m(8) - m(0) * m(5) * m(7)
}
