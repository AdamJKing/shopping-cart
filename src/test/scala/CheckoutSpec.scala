package com.github.adamjking

import model.*
import model.Item.{Apple, Orange}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {

  behavior of "Checking out"

  it should "total the price of all items in the cart" in {
    val result = checkout(Cart(items = Seq(Orange, Apple)))
    val expected = 85.pence
    result shouldBe expected
  }

  // A reasonable way of ensuring customers can still exit the checkout without buying something
  it should "return 0 for an empty basket" in {
    val result = checkout(Cart(items = Seq()))
    val expected = 0.pence
    result shouldBe expected
  }

  it should "discount pairs of apples (buy-one-get-one-free)" in {
    val cart = checkout(Cart(items = Seq(Apple, Apple)))
    val expected = Apple.price
    cart shouldBe expected
  }

  it should "discount multiple pairs of apples (buy-one-get-one-free)" in {
    val result = checkout(Cart(items = Seq.fill(5)(Apple)))
    val expected = Apple.price * 3
    result shouldBe expected
  }

  it should "discount one in three oranges (three-for-two)" in {
    val result = checkout(Cart(items = Seq.fill(3)(Orange)))
    val expected = Orange.price * 2
    result shouldBe expected
  }

  it should "discount multiple cases of one in three oranges (three-for-two)" in {
    val result = checkout(Cart(items = Seq.fill(7)(Orange)))
    val expected = Orange.price * 5
    result shouldBe expected
  }
}
