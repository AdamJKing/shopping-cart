package com.github.adamjking

import model.*
import model.Item.{Apple, Orange}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {

  behavior of "Checking out"

  it should "total the price of all items in the cart" in {
    val result = checkout(Cart(items = Seq(Apple, Apple, Orange, Apple)))
    val expected = 2.05.pounds
    result shouldBe expected
  }
  
  // A reasonable way of ensuring customers can still exit the checkout without buying something
  it should "return 0 for an empty basket" in {
    val result = checkout(Cart(items = Seq()))
    val expected = 0.pence
    result shouldBe expected
  }
}
