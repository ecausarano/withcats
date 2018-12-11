package com.esc.exercise

package object chap2 {
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]): Monoid[A] = monoid
  }

  object Semigroup {
    def apply[A](implicit semigroup: Semigroup[A]): Semigroup[A] = semigroup
  }
}
