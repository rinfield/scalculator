package com.github.rinfield.calc

import android.app.Activity
import android.os.Bundle

trait MyTypedActivity extends Activity with TypedActivity {
  implicit val self = this

  def contentView: Int

  def onCreate2(bundle: Bundle)

  override final def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(contentView)
    onCreate2(bundle)
  }
}
