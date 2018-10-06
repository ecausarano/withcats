package com.esc.exercise

trait Printable[A] {
  def format(a: A): String
}

object PrintableInstances {

  implicit val PrintableString: Printable[String] = (a: String) => a

  implicit val PrintableInt: Printable[Int] = (a: Int) => a.toString
}

object Printable {

  def format[A](a: A, printable: Printable[A]): String = printable.format(a)

  def print[A](a: A, printable: Printable[A]): Unit = println(printable.format(a))
}
