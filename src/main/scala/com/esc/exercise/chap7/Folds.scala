package com.esc.exercise.chap7


object Folds extends App {

  val alot = (1 to 1000000).toStream

  import cats.Eval
  import cats.Foldable
  import cats.instances.stream._

  val evald = Foldable[Stream].foldRight(alot, Eval.now(0L))((int, acc) => acc.map(_ + int))

  println(evald.value)

  println((1 to 1000000).toVector.foldRight(0L)(_ + _))
  println((1 to 1000000).toList.foldRight(0L)(_ + _))
}

import org.scalameter.api._
object RangeBenchmark extends Bench.LocalTime {
  val ranges = for {
    size <- Gen.range("size")(300000, 1500000, 300000)
  } yield 0 until size

  measure method "foldRight" in {
    using(ranges) curve "Vector" in { block =>
      block.toVector.foldRight(0L)(_+_)
    }

    using(ranges) curve "List" in { block =>
      block.toList.foldRight(0L)(_+_)
    }

    using(ranges) curve "Eval" in { block =>
      import cats.{Eval, Foldable}
      import cats.instances.stream._
      Foldable[Stream].foldRight(block.toStream, Eval.now(0L))((int, acc) => acc.map(_ + int)).value
    }

  }
}

object FoldingMonoids extends App {
  import cats.{Eval, Foldable}
  import cats.instances.int._
  import cats.instances.list._

  private val theList = List(1, 2, 3, 4, 5, 6)
  val monoidal: Int = Foldable[List].combineAll(theList)
  private val folded: Int = Foldable[List].foldMap(theList)(a => a)
  println(s"foldMap over theList: $folded")
  val explicit: Int = Foldable[List].foldRight(theList, Eval.now(0))((int, acc) => acc.map(_ + int)).value

  println(s"monoidal=$monoidal and explicit=$explicit are same=${monoidal == explicit}")
}

