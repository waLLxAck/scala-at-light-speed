package com.svilen

object ContextualAbstractions {

  /*
    1 - context parameters/arguments

   */

  val aList = List(2, 1, 3, 4)
  val anOrderedList: List[Int] = aList.sorted // (ordering)

  // Ordering
  given descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) // (a, b) => a > b

  def main(args: Array[String]): Unit = {
    println(descendingOrdering)
  }

}
