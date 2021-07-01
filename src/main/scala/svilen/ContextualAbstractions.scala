package com.svilen

object ContextualAbstractions {

  /*
    1 - context parameters/arguments

   */

  val aList = List(2, 1, 3, 4)
  val anOrderedList: List[Int] = aList.sorted // contextual argument -> (ordering)

  // Ordering
  given descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) // (a, b) => a > b
  // This changes the top method with the logic provided
  // analagous to implicit val

  trait Combinator[A] { // monoid
    def combine(x: A, y: A): A
  }

  // want to do -> combineAll(List(1, 2, 3, 4))
  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A =
    list.reduce(combinator.combine) // list.reduce((a, b) => combinator.combine(a, b))

  given intCombinator: Combinator[Int] = new Combinator[Int] { // when you write this v
    override def combine(x: Int, y: Int): Int = x + y
  }

  val theSum = combineAll(aList)  // this automatically uses it, for Int values.

  /*
    Given places (where the compiler searches for given instances)
    - local scope
    - imported scope (import yourpackage.given) <- imports all "given" instances of that package
    - the companions of all the types involved in the call (our case -> ln:26 combinator with type Int, as are
    invoking the method call with integers
   */

  // context bounds
  def combineAll_v2[A](list: List[A])(using Combinator[A]): A = ???
  def combineAll_v3[A: Combinator](list: List[A]): A = ??? // read it as -> "A" must have a given
  // type combinator in the scope
  /*
    where context args are useful
    - type classes
    - dependency injection
    - context-dependent functionality -> restricting functionality of some type but not others
    - type-level programming
   */

  // extension methods -> you can add additional methods to a type after it was defined
    // even if you don't have the source of that file

  case class Person(name:String) {
    def greet(): String = s"Hi. Name's $name. I love Scala."
  }

  extension (string: String) // creating an extension to the String type (type enrichment)
    def greet(): String = new Person(string).greet()

  val danielsGreeting = "Daniel".greet()
  println(danielsGreeting)

  extension (int: Int) { // extension to the Int data type
    def timesTwo(): Unit = {
      println(int*2)
    }
  }

  25.timesTwo()
  636.timesTwo()

  // POWER

  extension [A] (list: List[A])
    def combineAllValues(using combinator: Combinator[A]): A =
      list.reduce(combinator.combine)

  val theSumOfExtensionList = aList.combineAllValues

  def main(args: Array[String]): Unit = {
    println(anOrderedList)
    println(theSum)
  }

}
