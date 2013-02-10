package com.github.rinfield.scalc.logic

object Calc extends Calc(DispNum.empty, None, None, None)

case class Calc(disp: DispNum, x: Option[Num], op: Option[OpKey], y: Option[Num]) {
  def apply(k: Key): Calc = k match {
    case Key.C => Calc
    case Key.Equal => (x, op, y) match {
      case (Some(x), Some(op), Some(y)) => copy(disp = op(x, y).toDispNum, x = Some(op(x, y)))
      case _                            => this
    }
    case k: NumsKey => (x, op, y) match {
      case (Some(x), _, None)     => copy(disp = DispNum.empty(k), y = Some(DispNum.empty(k).toNum))
      case (Some(x), _, Some(y))  => copy(disp = disp(k), y = Some(disp(k).toNum))
      case (Some(x), Some(op), _) => copy(disp = DispNum.empty(k))
      case (_, _, _)              => copy(disp = disp(k))
    }
    case k: OpKey => (x, op, y) match {
      case (None, _, None) => copy(x = Some(disp.toNum), op = Some(k), y = None)
    }
  }
}



