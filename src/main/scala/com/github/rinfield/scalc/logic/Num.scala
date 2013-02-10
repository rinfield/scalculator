package com.github.rinfield.scalc.logic

case class Num(v: Double) {
  def +(y: Num): Num = Num(v + y.v)
  def -(y: Num): Num = Num(v - y.v)
  def *(y: Num): Num = Num(v * y.v)
  def /(y: Num): Num = Num(v / y.v)
  def toDispNum(): DispNum = DispNum(v.toString)
}
case object DispNum {
  val empty = DispNum("")
  def apply(v: String): DispNum = new DispNum(v)
}
class DispNum(x: String) {
  val v = x match {
    case ""  => x
    case "-" => x
    case "." => "0."
    case _   => normalize(x)
  }

  private def normalize(v: String) = v.toDouble.toString match {
    case x if x.endsWith(".0") => x.split("""\.""")(0)
    case x                     => x
  }

  def apply(k: NumsKey): DispNum = k match {
    case Key.Point     => if (x.contains(".")) this else DispNum(x + ".")
    case Key.PlusMinus => if (x.startsWith("-")) DispNum(x.tail) else DispNum("-" + x)
    case k: NumKey     => DispNum(x + k.v)
  }
  def toNum: Num = x match {
    case ""   => Num(0)
    case "-"  => Num(0)
    case "."  => Num(0)
    case "-." => Num(0)
    case _    => Num(x.toDouble)
  }
  override def toString() = v
}
