package com.esc.exercise.chap2

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

  // nice possibility: pass the combinator as arg
  def funzione(x: Boolean, y: Boolean, come: Monoid[Boolean]) = come.combine(x, y)
  println(funzione(x = true, y = false, And))
}
