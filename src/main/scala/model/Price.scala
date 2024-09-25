package com.github.adamjking
package model

import java.text.NumberFormat
import java.util.Locale

// Assumed to be in GBP
opaque type Price = Int

extension (price: Price)
  def show: String = NumberFormat.getCurrencyInstance(Locale.UK).format(price / 100.0)

object Price:
  given Numeric[Price] = Numeric.IntIsIntegral
  given Equiv[Price] = Equiv.Int

extension [A](value: Int)
  def pound: Price = value.pounds
  def pounds: Price = value * 100
  def pence: Price = value

extension[A] (value: Double)
  def pound: Price = value.pounds
  def pounds: Price = (value * 100).ceil.toInt
