package com.yfbx.coroutinesdemo.global.ext

import android.annotation.SuppressLint
import android.widget.Toast
import com.yfbx.coroutinesdemo.global.App


/**
 * Toast 工具类
 *
 * @author xuzeyang
 * @date 2017/10/25
 */
private var toast: Toast? = null

@SuppressLint("ShowToast")
fun toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    if (message == null) return
    if (toast == null) {
        toast = Toast.makeText(App.app, message, duration)
    } else {
        toast?.setText(message)
    }
    toast?.show()
}

@SuppressLint("ShowToast")
fun toast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    if (toast == null) {
        toast = Toast.makeText(App.app, message, duration)
    } else {
        toast?.setText(message)
    }
    toast?.show()
}