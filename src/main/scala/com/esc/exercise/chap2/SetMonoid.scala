package com.esc.exercise.chap2

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

object Semigroup {
  def apply[A](implicit semigroup: Semigroup[A]): Semigroup[A] = semigroup
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] = monoid
}

object SetMonoid extends App {

  implicit def Union[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty
    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

  implicit def Intersection[A]: Semigroup[Set[A]] = new Semigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }

  val intSet = Monoid[Set[Int]]
  println(intSet.combine(Set(1, 2), Set(2, 3)))

  val intSetSemigroup = Semigroup[Set[Int]]
  println(intSetSemigroup.combine(Set(1, 2), Set.empty))
}
