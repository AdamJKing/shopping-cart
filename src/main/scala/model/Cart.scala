package com.github.adamjking
package model

import model.Item.{Apple, Orange}

import scala.Function.const

case class Cart(items: Map[Item, Int]):
  val total: Price = items.view.map {
    case (Apple, quantity) =>
      if quantity >= 2 then Apple.price * (quantity / 2.0).ceil.toInt
      else Apple.price * quantity

    case (Orange, quantity) =>
      if quantity >= 3 then
        Orange.price * (quantity - (quantity / 3.0).ceil.toInt)
      else Orange.price * quantity
  }.sum

object Cart:
  def apply(items: Seq[Item]): Cart =
    Cart(items.groupMapReduce(identity)(const(1))(_ + _))
