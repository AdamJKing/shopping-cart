package com.github.adamjking
package model

type Offer = Cart => Cart

object Offer:
  def buyOneGetOneFree(target: Item): Offer = cart =>
    Cart(cart.items.updatedWith(target) {
      case None           => None
      case Some(quantity) => 
        // remove one in two items
        Some(quantity - (quantity / 2.0).toInt)
    })
    
  def twoForOne(target: Item): Offer = cart =>
    Cart(cart.items.updatedWith(target) {
      case None => None
      case Some(quantity) => 
        // remove one in three items
        Some(quantity - (quantity / 3.0).toInt)
    })
