package com.yfbx.coroutinesdemo.global.ext

import android.graphics.Color
import android.support.annotation.ColorRes
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.yfbx.coroutinesdemo.R
import com.yfbx.coroutinesdemo.global.App

/**
 * Author: Edward
 * Date: 2019-08-01
 * Description:
 */


/**
 * show error
 */
fun showError(msg: String) {
    showToast(msg, R.color.labelPink)
}

/**
 * show msg
 */
fun showMsg(msg: String) {
    showToast(msg, R.color.labelGreen)
}


/**
 * Toast
 */
fun showToast(msg: String, @ColorRes color: Int, duration: Int = Toast.LENGTH_SHORT) {
    val context = App.app

    val textView = TextView(context)
    textView.layoutParams = ViewGroup.LayoutParams(matchParent(), dpInt(48f))
    textView.gravity = Gravity.CENTER
    textView.setTextColor(Color.WHITE)
    textView.textSize = 16f
    textView.setBackgroundResource(color)
    textView.text = msg
    val layout = LinearLayout(context)
    layout.addView(textView)

    val toast = Toast(context)
    toast.duration = duration
    toast.view = layout
    toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL or Gravity.CLIP_VERTICAL, 0, 0)
    toast.show()
}