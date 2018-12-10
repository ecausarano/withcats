package com.esc.exercise.chap2.cats

object CatsMonoid extends App {

  import cats.Monoid
  import cats.instances.string._
  println(Monoid[String].combine("Hi ", "there"))

  import cats.instances.int._
  import cats.instances.option._

  val a = Option(22)
  val b = Option(20)

  private val result: Option[Int] = Monoid[Option[Int]].combine(a, b)

  println(result)

  import cats.syntax.semigroup._

  println(a |+| b |+| Monoid[Option[Int]].empty)

}
