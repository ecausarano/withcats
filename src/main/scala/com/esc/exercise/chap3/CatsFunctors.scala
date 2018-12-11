package com.esc.exercise.chap3


object CatsFunctors extends App {

  import cats.Functor
  import cats.instances.list._
  import cats.instances.option._

  val l1 = List(1, 2, 3)
  val l2 = Functor[List].map(l1)(_ * 3)
  println(l2)

  val op1 = Option(123)
  val op2 = Functor[Option].map(op1)(_ - 123)
  println(op2)

  val by2 = { x: Int => x * 2 }
  val by2Opt = Functor[Option].lift(by2)
  println(by2Opt(Option(5)))
}

object FunFunctor extends App {
  import cats.instances.function._
  import cats.syntax.functor._

  val f1 = { x: Int => x + 1    }
  val f2 = { x: Int => x * 2    }
  val f3 = { x: Int => x + "!"  }

  val f4 = f1.map(f2).map(f3)
  println(f4(123))

  import cats.Functor
  import scala.language.higherKinds

  def doMath[F[_]](from: F[Int])(implicit functor: Functor[F]) =
    from.map(value => value * 2)

  import cats.instances.list._
  import cats.instances.option._
  println(doMath(Option(123123)))
  println(doMath(List(123123, 234234)))
}

object LeafFunctor extends App {
  import cats.Functor

  sealed trait Tree[+A]
  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  final case class Leaf[A](value: A) extends Tree[A]

  implicit def treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Leaf(value) => Leaf(f(value))
      case Branch(left, right) =>
        Branch(map(left)(f), map(right)(f))
    }
  }

  // must add type declaration to tree val or pretty syntax won't work...
  private val tree: Tree[Int] = Branch(
    Branch(Leaf(1), Leaf(2)),
    Leaf(45)
  )

  // pedantic syntax
  println(Functor[Tree].map(tree)(_ * 2))

  // pretty syntax
  import cats.syntax.functor._
  println(tree.map(_ * 2))
}

