package com.github.rinfield.scalc.logic

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.github.rinfield.scalc.logic.Key._

class CalcSpec extends FlatSpec with ShouldMatchers {
  "計算機は" should "１つ目の数に整数を入力できること" in {
    Calc.disp.toString should equal("")
    Calc(Num1).disp.toString should equal("1")
    Calc(Num1)(Num2).disp.toString should equal("12")
    Calc(Num1)(Num2)(Num3).disp.toString should equal("123")
  }

  it should "１つ目の数に小数を入力できること" in {
    Calc(Point).disp.toString should equal("0.")
    Calc(Num1)(Point).disp.toString should equal("1") // TODO better to be "1."?
    Calc(Num1)(Point)(Num2).disp.toString should equal("1.2")
  }

  it should "2つ目の整数を入力できること" in {
    val target = Calc(Num1)(OpAdd)
    target(Num1).disp.toString should equal("1")
    target(Num1)(Num2).disp.toString should equal("12")
    target(Num1)(Num2)(Num3).disp.toString should equal("123")
  }

  it should "加算ができること" in {
    Calc(Num1)(OpAdd)(Num2)(Equal).disp.toString should equal("3")
  }

  it should "減算ができること" in {
    Calc(Num3)(OpSubtract)(Num1)(Equal).disp.toString should equal("2")
  }

  it should "乗算ができること" in {
    Calc(Num2)(OpMultiply)(Num3)(Equal).disp.toString should equal("6")
  }

  it should "除算ができること" in {
    Calc(Num6)(OpDivide)(Num2)(Equal).disp.toString should equal("3")
  }
}