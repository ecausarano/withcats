package com.esc.exercise

import Printable._
import PrintableSyntax._

// see ex p.19
object Main extends App {
  final case class Cat(name: String, age: Int, color: String)

  implicit val printableCat: Printable[Cat] = (c: Cat) =>
    s"${c.name} is a ${c.age} year-old ${c.color} cat."

  val meow = Cat("pussy", 1, "black")
  print(meow)

  meow.print

}
