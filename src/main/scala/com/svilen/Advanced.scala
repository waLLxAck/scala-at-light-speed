package com.svilen

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object Advanced extends App {

  // lazy evaluation (useful in infinite collections)
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffects = {
    println("I am so very lazy")     // due to lazy keyword, it won't print until the variable is used somewhere
    43 // the variable has the last value assigned to it.
  }

  val eagerValue = lazyValueWithSideEffects + 1

  // "pseudo-collections" (Option, Try)
  def methodWhichCanReturnNull(): String = "Hello, Scala."

  /*
  if (methodWhichCanReturnNull() == null) {
    // defensive code against nulls
  }
  */

  // options (used for eliminating null checks)
  val anOption = Option(methodWhichCanReturnNull()) // if valid value -> Some("Hello, Scala.") // if invalid -> None
  // option -> "collection" which contains at most one element

  val stringProcessing = anOption match {
    case Some(string: String) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing"
  }
  // options has the compositional functions -> map, flatMap, filter

  // try -> "collection" with either a value if the code went well, or an exception if the code threw one.
  def methodWhichCanThrowException(): String = throw new RuntimeException

  /*
  * try {
  *   methodWhichCanThrowException()
  * } catch (Exception e) {
  *   case NullPointer...
  *   case OtherException...
  * }*/

  val aTry = Try(methodWhichCanThrowException())
  // if valid value -> returns Success(validValue) // if invalid -> returns Failure(exception)
  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(exception) => s"I have stumbled upon an error. $exception"
  }
  // try has the compositional functions -> map, flatMap, filter

  /**
   * Evaluate something on another thread
   * (asynchronous programming)
   */

  // future
  // contains a value when it's evaluated (not before)
  // it's composable with map, flatMap, filter
  // monads
  val aFuture = Future { // Evaluated on another thread
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.") //Main thread finishes before this is reached
    67
  }

  // implicits basics

  // implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitInt: Int = 46
  println(aMethodWithImplicitArgs) // aMethodWithImplicitArgs(myImplicitInt)

  // implicit conversions
  implicit class MyRichInteger(num: Int) {
    def isEven: Boolean = num % 2 == 0
  }

  println(23.isEven) // System.out.println(new MyRichInteger(23).isEven())
  // use this carefully!


}
