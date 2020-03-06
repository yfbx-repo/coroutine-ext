package com.yfbx.coroutinesdemo.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Author Edward
 * Date 2019/5/20 0020
 * Description:
 */

fun dp(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
}

fun dp(dp: Int): Int {
    return dp(dp.toFloat()).toInt()
}

fun sp(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sp, Resources.getSystem().displayMetrics)
}

fun sp(sp: Int): Int {
    return sp(sp.toFloat()).toInt()
}

fun matchParent(): Int {
    return -1
}

fun wrapContent(): Int {
    return -2
}


fun screenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun screenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}
