package scalashop

import org.junit.Test
import scala.collection._

class ParallelSuite extends BlurSuite {
  @Test def `parallel of two works`: Unit = {
    val elements1: List[Int] = List.range(0, 10).map(_ => 1)
    val elements2: List[Int] = List.range(0, 10).map(_ => 2)
    val elements3: List[Int] = List.range(0, 10).map(_ => 3)
    val elements4: List[Int] = List.range(0, 10).map(_ => 4)
    val elements5: List[Int] = List.range(0, 10).map(_ => 5)

    val result: mutable.Map[Int, List[Int]] = mutable.Map.empty[Int, List[Int]]

    def updateResult(res: mutable.Map[Int, List[Int]], ind: Int, value: List[Int]): Unit = {
      res.addOne(ind, res.getOrElse(ind, Nil) ++ value)
    }

    List(
      task(elements1.foreach(i => updateResult(result, i, List(i)))),
      task(elements2.foreach(i => updateResult(result, i, List(i)))),
      task(elements3.foreach(i => updateResult(result, i, List(i)))),
      task(elements4.foreach(i => updateResult(result, i, List(i)))),
      task(elements5.foreach(i => updateResult(result, i, List(i))))
    ).foreach(task => task.join())

    assert(result.toList.sortBy(_._1) == Map(
      1 -> elements1,
      2 -> elements2,
      3 -> elements3,
      4 -> elements4,
      5 -> elements5).toList.sortBy(_._1)
    )
  }
}
