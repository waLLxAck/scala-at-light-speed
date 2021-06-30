package com.svilen

object PatternMatching extends App {

  // switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }
  // Pattern Matching is an EXPRESSION
  println(order)

  // Pattern matching can decompose case classes
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 43)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old."
    case _ => "Something else."
  }
  println(personGreeting)

  // deconstructing tuples
  var aTuple = ("Bon Jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre."
    case _ => "I don't know what you're talking about."
  }

  // decomposing lists
  val aList = List(1, 2, 3)
  val listDescription = aList match {
    case List(_, 2, _) => println("List containing 2 on its second position")
    case _ => "Unknown list." // if PM doesn't match anything, it will throw a MatchError
  }

  // PM will try all cases in sequence!
  // case _=> "unknown list" -> will execute if put first
  // case List(_, 2, _) => println("List containing 2 on its second position)

}
