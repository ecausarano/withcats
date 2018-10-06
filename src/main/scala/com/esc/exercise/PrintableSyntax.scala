package com.esc.exercise

object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String =
      Printable.format(value)

    def print(implicit p: Printable[A]): Unit =
      Printable.print(value)
  }
}
