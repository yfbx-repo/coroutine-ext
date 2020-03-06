package com.yfbx.coroutinesdemo.utils

import android.annotation.SuppressLint
import android.widget.Toast
import com.yfbx.coroutinesdemo.App

/**
 * Author: Edward
 * Date: 2019-08-01
 * Description:
 */
private var toast: Toast? = null

@SuppressLint("ShowToast")
fun toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    if (message == null) return
    toast?.cancel()
    toast = Toast.makeText(App.app, message, duration)
    toast?.show()
}

@SuppressLint("ShowToast")
fun toast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast?.cancel()
    toast = Toast.makeText(App.app, message, duration)
    toast?.show()
}