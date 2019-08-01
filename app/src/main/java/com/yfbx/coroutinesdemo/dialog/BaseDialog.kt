package com.yfbx.coroutinesdemo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.yfbx.coroutinesdemo.R

/**
 * Author Edward
 * Date 2019/5/11 0011
 * Description:
 */
abstract class BaseDialog(context: Context) : Dialog(context, R.style.Dialog) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setWindow()
    }

    private fun setWindow() {
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.gravity = gravity()
        params?.dimAmount = alpha()
        window?.attributes = params
        if (anim() != -1) {
            window?.setWindowAnimations(anim())
        }
    }

    /**
     * Layout
     */
    abstract fun getLayout(): Int

    /**
     * Gravity
     */
    open fun gravity(): Int {
        return Gravity.CENTER
    }

    /**
     * anim
     */
    open fun anim(): Int {
        return -1
    }

    /**
     * background alpha
     */
    open fun alpha(): Float {
        return 0.5f
    }

}