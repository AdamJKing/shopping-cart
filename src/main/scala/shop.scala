package com.github.adamjking

import model.*

@main def main(args: String*): Unit =
  val items = args.map(_.toLowerCase).map {
    case "apple"  => Item.Apple
    case "orange" => Item.Orange
    case unknown =>  throw IllegalArgumentException(s"We don't sell ${unknown}s!")
  }

  println(Cart(items).total.show)
