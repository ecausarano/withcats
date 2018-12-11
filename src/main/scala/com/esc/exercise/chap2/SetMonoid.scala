package com.esc.exercise.chap2


object SetMonoid extends App {

  implicit def Union[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty
    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
  }

  implicit def Intersection[A]: Semigroup[Set[A]] = (x: Set[A], y: Set[A]) => x intersect y

  val intSet = Monoid[Set[Int]]
  println(intSet.combine(Set(1, 2), Set(2, 3)))

  val intSetSemigroup = Semigroup[Set[Int]]
  println(intSetSemigroup.combine(Set(1, 2), Set.empty))
}
