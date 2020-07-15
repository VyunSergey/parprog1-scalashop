package scalashop.time

trait Timed[A] {
  def timed(a: => A, message: String): A = {
    val start = System.nanoTime()
    val result = a
    val finish = System.nanoTime()
    val time = BigDecimal((finish - start) / 1000.0 / 1000.0 / 1000.0)
      .setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
    println(s"$message with time: $time sec")
    result
  }
}

object Timed {
  implicit def impl[A]: Timed[A] = new Timed[A] {}

  def apply[A](implicit inst: Timed[A]): Timed[A] = inst
}
