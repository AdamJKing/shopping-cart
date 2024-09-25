package com.github.adamjking

import model.*
import model.Item.{Apple, Orange}

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {

  behavior of "Checking out"

  it should "total the price of all items in the cart" in {
    val result = Cart(items = Seq(Orange, Apple)).total
    val expected = 85.pence
    result shouldBe expected
  }

  // A reasonable way of ensuring customers can still exit the checkout without buying something
  it should "return 0 for an empty basket" in {
    val result = Cart(items = Seq()).total
    val expected = 0.pence
    result shouldBe expected
  }

  it should "discount pairs of apples (buy-one-get-one-free)" in {
    val result = Cart(items = Seq(Apple, Apple)).total
    val expected = Apple.price
    result shouldBe expected
  }

  it should "discount multiple pairs of apples (buy-one-get-one-free)" in {
    val result = Cart(items = Seq.fill(5)(Apple)).total
    val expected = Apple.price * 3
    result shouldBe expected
  }

  it should "discount one in three oranges (three-for-two)" in {
    val result = Cart(items = Seq.fill(3)(Orange)).total
    val expected = Orange.price * 2
    result shouldBe expected
  }

  it should "discount multiple cases of one in three oranges (three-for-two)" in {
    val result = Cart(items = Seq.fill(6)(Orange)).total
    val expected = Orange.price * 4
    result shouldBe expected
  }
}
