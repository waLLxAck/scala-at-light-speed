package com.svilen

object FunctionalProgramming extends App {

  // Scala is Object Oriented
  class Person(name: String) {
    def apply(age: Int): Unit = println(s"I have aged $age years.")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  bob(43) // INVOKING bob as a function === bob.apply(43)

  /*
    Scala runs on JVM
    Functional programming:
    - compose functions
    - pass functions as arguments
    - return functions as results

    Conclusion: FunctionX = Function1, Function2, ... Function22
   */

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23) //24
  simpleIncrementer(23) // simpleIncrementer.apply(23) = 24
  // defined a function!

  // all Scala functions are instances of these FunctionX types

  val stringConcatenator = new Function2[String, String, String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }

  stringConcatenator("I love ", "Scala.") // "I love Scala."

  // syntax sugars (code that will replace ^)
  val doubler: Int => Int = (x: Int) => x * 2
  doubler(4) // 8

  // removing the type all together
  val doubler2 = (x: Int) => x * 2
  doubler2(5) // 10

  /*
  Equivalent:

  val doubler: Function1[Int, Int] = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }
  */

  // higher-order functions (take functions as args/return functions as results, or both
  val aMappedList = List(1, 2, 3).map( x => x + 1) // HOF -> List(2, 3, 4)
  println(aMappedList)
  val aFlatMappedList = List(1, 2, 3).flatMap(x => List(x, x * 2)) // Merges the lists inside into a single list -> List(1, 2, 2, 4, 3, 6)
  println(aFlatMappedList)
  val aFilteredList = List(1, 2, 3, 4, 5).filter(x => x <= 3)
  println(aFilteredList)
  val aFilteredListShortened = List(1, 2, 3, 4, 5).filter(_ <= 3) // Equivalent to x => x <= 3
  println(aFilteredListShortened)

  // all pairs between the numbers 1, 2, 3 and the letters 'a', 'b', 'c'
  val allPairs = List(1, 2, 3).flatMap(x => List('a', 'b', 'c').map(x+_.toString))
  val allPairs2 = List(1, 2, 3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  println(allPairs)
  println(allPairs2)

  // for comprehensions (equivalent to ^)
  val alternativePairs = for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c', 'd')
    extraNumber <- List(4, 5, 6) // testing for exponential growth of values
    asd <- List(1,2)
  } yield s"$number-$letter-$extraNumber-$asd"

  println(alternativePairs)

  //Collections

  // lists
  val aList = List(1, 2, 3, 4, 5)
  val firstElement = aList.head
  val tail = aList.tail
  val aPrependedList = 0 :: aList // List(0, 1, 2, 3, 4, 5)
  println(aPrependedList)
  val anExtendedList = 0+: 3+: aList :+6 :+5  // List(0, 3, 1, 2, 3, 4, 5, 6, 5)
  println(anExtendedList)

  // sequences (trait - abstract type) - you can access an element from a given index
  val aSequence: Seq[Int] = Seq(1, 2, 3) // Seq.apply(1, 2, 3)
  val accessedElement = aSequence(1) // aSequence.apply(1) -> 2
  println(accessedElement)

  // vectors (fast access time, same methods as lists and sequences
  val aVector = Vector(1, 2, 3, 4, 5)

  // sets
  val aSet = Set(1, 2, 3, 4, 1, 2, 3)
  val setHas5 = aSet.contains(5) // false
  val anAddedSet = aSet + 5 // Set(1, 2, 3, 4, 5) -> not necessarily ordered
  val aRemovedSet = aSet - 3 // SetSet(1, 2, 4)

  // ranges
  val aRange = 1 to 1000
  val aRange2 = Range(1, 1000)
  println(aRange, aRange2) // (Range 1 to 1000,Range 1 until 1000)
  val twoByTwo = aRange.map(2*_).toList
  println(twoByTwo)

  // tuples (group of values under the same value)
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val aPhonebook: Map[String, Int] = Map (
    ("Daniel", 6437812),
    "Jane" -> 327285 // ("Jane", 327285)
  )

}
