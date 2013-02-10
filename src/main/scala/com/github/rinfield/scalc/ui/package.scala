package com.github.rinfield.scalc

import android.view.View
import android.widget.Button
package object ui {
  implicit def enrichTypedResouce[A](tr: TypedResource[A]) = new {
    def get(implicit ta: TypedActivity): A = ta.findView(tr)
  }

  implicit def enrichView[A <: View](view: A) = new {
    def onClick(f: A => Unit) =
      view setOnClickListener new View.OnClickListener {
        def onClick(v: View): Unit = f(v.asInstanceOf[A])
      }
  }
}