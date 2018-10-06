package com.esc.exercise

object EqOb extends App {

  // to be fair, Intellij catches it, but Eq rocks!
  private val filtered: List[Some[Int]] = List(1, 2, 3).map(i => Some(i)).filter(i => i == 1)

  println(filtered)

}

object CatsEq extends App {

  case class Cat(name: String, age: Int, color: String)

  val garfield = Cat("Garfield",   38, "orange and black")
  val heath = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(garfield)
  val optionCat2 = Option.empty[Cat]

  import cats.Eq
  import cats.syntax.eq._

  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (a, b) =>
    import cats.instances.int._
    import cats.instances.string._
    (a.age === b.age) && (a.color === b.color) && (a.name === b.name)
  }

  println(s"Garfield and Heathcliff are same:${garfield === heath}")
  println(s"Garfield and Heathcliff are different:${garfield =!= heath}")

  import cats.instances.option._
  println(s"comparing Garf with none: ${optionCat1 === optionCat2}")
}
