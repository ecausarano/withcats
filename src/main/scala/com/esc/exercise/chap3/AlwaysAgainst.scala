package com.esc.exercise.chap3

object AlwaysAgainst extends App {
  trait Printable[A] {
    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] = (value: B) => Printable.this.format(func(value))
  }

  // I guess this is the "syntax" implementation that does the "type based dispatch" to the right implicit
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

  implicit val stringPrintable: Printable[String] = (value: String) => "\"" + value + "\""

  implicit val booleanPrintable: Printable[Boolean] = (value: Boolean) => if (value) "yes Sir!" else "forget it!"

  final case class Box[T](value: T)

//  implicit def boxPrintable[A](b: Box[A])(implicit p: Printable[A]): Printable[Box[A]] = new Printable[Box[A]] {
//    override def format(box: Box[A]): String = p.format(box.value)
//  }
  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] = p.contramap[Box[A]](_.value)

  println(format(Box("hello dude!")))
  println(format(Box(true)))

//  implicit val intPrintable: Printable[Int] = (value: Int) => "\"" + value + "\""
//  println(format(Box(123)))
}
