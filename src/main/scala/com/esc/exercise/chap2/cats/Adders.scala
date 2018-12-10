package com.esc.exercise.chap2.cats

object IntAdder extends App {

  def addSimple(items: List[Int]): Int = items.sum

  import cats.Monoid
  import cats.instances.int._
  import cats.syntax.semigroup._
  def add(items: List[Int]): Int = items.fold(Monoid.empty[Int])(_ |+| _)

  println(add(List(1, 2, 3)))
}

object GenericAdder extends App {

  import cats.Monoid
  import cats.syntax.semigroup._

  def add[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

  import cats.instances.int._
  import cats.instances.option._
  println(add(List(Some(1), Some(2), None)))
}

object AddOrders extends App {

  case class Order(totalCost: Double, quantity: Double)

  import cats.Monoid
  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)
    override def combine(x: Order, y: Order): Order =
      Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }

  import cats.syntax.semigroup._

  def add[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

  println(add(List(Order(1.0 , 2.0), Order(2.0, 1.0))))
}