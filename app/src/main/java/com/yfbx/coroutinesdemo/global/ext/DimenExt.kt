package com.yfbx.coroutinesdemo.global.ext

import android.content.res.Resources
import android.util.TypedValue

/**
 * Author Edward
 * Date 2019/5/20 0020
 * Description:
 */

fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
}

fun dpInt(dp: Float): Int {
    return dp2px(dp).toInt()
}

fun sp2px(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sp, Resources.getSystem().displayMetrics)
}

fun spInt(sp: Float): Int {
    return sp2px(sp).toInt()
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
