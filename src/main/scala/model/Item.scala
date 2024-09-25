package com.github.adamjking
package model

enum Item(val price: Price):
  case Apple extends Item(60.pence)
  case Orange extends Item(25.pence)
