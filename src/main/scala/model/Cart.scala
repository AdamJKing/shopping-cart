package com.github.adamjking
package model

import scala.Function.const

case class Cart(items: Map[Item, Int]):
  def applyOffer(offer: Offer): Cart = offer(this)
  val total: Price = 
    items.view.map { case (item, quantity) => item.price * quantity }.sum

object Cart:
  def apply(items: Seq[Item]): Cart =
    Cart(items.groupMapReduce(identity)(const(1))(_ + _))
