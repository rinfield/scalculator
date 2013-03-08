package com.github.rinfield.scalc.logic

object Key {
  case object C extends Key
  case object Equal extends Key

  case object Num0 extends NumKey("0")
  case object Num1 extends NumKey("1")
  case object Num2 extends NumKey("2")
  case object Num3 extends NumKey("3")
  case object Num4 extends NumKey("4")
  case object Num5 extends NumKey("5")
  case object Num6 extends NumKey("6")
  case object Num7 extends NumKey("7")
  case object Num8 extends NumKey("8")
  case object Num9 extends NumKey("9")
  case object Point extends NumsKey
  case object PlusMinus extends NumsKey

  case object OpAdd extends OpKey((x, y) => x + y)
  case object OpSubtract extends OpKey((x, y) => x - y)
  case object OpMultiply extends OpKey((x, y) => x * y)
  case object OpDivide extends OpKey((x, y) => x / y)
} 
sealed trait Key
sealed trait NumsKey extends Key
sealed abstract class NumKey(val v: String) extends NumsKey
sealed abstract class OpKey(f: (Num, Num) => Num) extends Key {
  def apply(x: Num, y: Num) = f(x, y)
}
