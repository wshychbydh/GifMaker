package com.cool.eye.gifmaker.model

import android.graphics.Bitmap

/**
 *Created by ycb on 2019/8/15 0015
 */
class GifModel {
  var repeat: Int = 1
  var frames = arrayListOf<Bitmap>()
  var path: String? = null
  var delay: Int = 500

  fun addFrame(bitmap: Bitmap) {
    frames.add(bitmap)
  }

  fun reset() {
    frames.clear()
    path = null
    delay = 100
    repeat = 1
  }
}