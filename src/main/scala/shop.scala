package com.github.adamjking

import model.*
import model.Offer.{buyOneGetOneFree, twoForOne}

@main def main(args: String*): Unit =
  val items = args.map(_.toLowerCase).map {
    case "apple"  => Item.Apple
    case "orange" => Item.Orange
    case unknown =>
      throw IllegalArgumentException(s"We don't sell ${unknown}s!")
  }

  println(checkout(Cart(items)).show)

def checkout(cart: Cart): Price = cart
  .applyOffer(buyOneGetOneFree(Item.Apple))
  .applyOffer(twoForOne(Item.Orange))
  .total
