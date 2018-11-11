package com.esc.exercise.chap1

import cats.Show
import cats.syntax.show._

object MainCats extends App {

  case class Cat(name: String, age: Int, color: String)

  implicit val showCat: Show[Cat] = Show.show[Cat] { cat =>
    s"${cat.name} is a ${cat.age} old ${cat.color} cat"
  }

  val meouw = Cat("Puss", 2, "tabby")
  println(meouw.show)

}
