package com.github.rinfield.calc

import android.app.Activity
import android.os.Bundle

trait MyTypedActivity extends Activity with TypedActivity {
  implicit val self:TypedActivity = this

  def contentView: Int

  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(contentView)
  }
}
