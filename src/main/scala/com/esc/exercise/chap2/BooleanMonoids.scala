package com.esc.exercise.chap2

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] = monoid
}

object BooleanMonoids extends App {

  implicit val And: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val Or: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val Xor: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = {
      (x && !y) || (!x && y)
    }
  }

  println(And.combine(true, And.empty))
  println(Or.combine(true, Or.empty))
  println(Xor.combine(true, Xor.empty))

  println(And.combine(false, And.empty))
  println(Or.combine(false, Or.empty))
  println(Xor.combine(false, Xor.empty))


}
