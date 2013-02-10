package com.github.rinfield.scalc.ui
import android.os.Bundle
import com.github.rinfield.scalc.logic._

class MainActivity extends MyTypedActivity {
  val contentView = R.layout.main
  var calc: Calc = Calc

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle);
    val display = TR.display.get

    val buttons = Map(
      TR.button0.get -> Key.Num0,
      TR.button1.get -> Key.Num1,
      TR.button2.get -> Key.Num2,
      TR.button3.get -> Key.Num3,
      TR.button4.get -> Key.Num4,
      TR.button5.get -> Key.Num5,
      TR.button6.get -> Key.Num6,
      TR.button7.get -> Key.Num7,
      TR.button8.get -> Key.Num8,
      TR.button9.get -> Key.Num9,

      TR.buttonAdd.get -> Key.OpAdd,
      TR.buttonSubtract.get -> Key.OpSubtract,
      TR.buttonMultiply.get -> Key.OpMultiply,
      TR.buttonDivide.get -> Key.OpDivide,

      TR.buttonC.get -> Key.C,
      TR.buttonPlusMinus.get -> Key.PlusMinus,
      TR.buttonPoint.get -> Key.Point,
      TR.buttonEqual.get -> Key.Equal)

    buttons.foreach {
      case (b, k) =>
        b.onClick { b =>
          calc = calc(k)
          display.setText(calc.disp.toString)
        }
    }
  }
}
