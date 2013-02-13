package com.github.rinfield.scalc.logic

object Calc extends Calc(DispNum.empty, AMode, None, None, None)

case class Calc(disp: DispNum, mode: Mode, x: Option[Num], op: Option[OpKey], y: Option[Num]) {
  def apply(k: Key): Calc = k match {
    case Key.C => Calc
    case Key.Equal => onEqual
    case k: NumsKey => onNumKey(k)
    case k: OpKey => onOpKey(k)

  }
  private def onEqual() = mode match {
    case BMode => (x, op, y) match {
      case (Some(x), Some(op), Some(y)) => {
        val res = op(x, y)
        copy(mode = Result, disp = res.toDispNum, x = Some(res))
      }
    }
    case _ => copy(mode = Result)
  }
  private def onNumKey(k: NumsKey) = mode match {
    case AMode => copy(mode = AMode, disp = disp(k))
    case CMode => copy(mode = BMode, disp = DispNum.empty(k), y = Some(DispNum.empty(k).toNum))
    case BMode => copy(mode = BMode, disp = disp(k), y = Some(disp(k).toNum))
    case Result => Calc(k)
  }
  private def onOpKey(k: OpKey) = mode match {
    case AMode => copy(mode = CMode, op = Some(k), x = Some(disp.toNum))
    case CMode => copy(mode = CMode, op = Some(k))
    case BMode => copy(mode = CMode, op = Some(k), y = Some(disp.toNum))
    case Result => {
      val result = k(x.get, y.get)
      copy(mode = CMode, disp = result.toDispNum, x = Some(result))
    }
  }
}

sealed trait Mode
case object AMode extends Mode
case object CMode extends Mode
case object BMode extends Mode
case object Result extends Mode

