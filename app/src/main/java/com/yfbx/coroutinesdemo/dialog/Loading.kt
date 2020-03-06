package com.yfbx.coroutinesdemo.dialog

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.yfbx.coroutinesdemo.R
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * Author: Edward
 * Date: 2019-07-20
 * Description:
 */
class Loading(context: Activity) : BaseDialog(context) {

    companion object {
        fun show(context: Activity): Loading {
            val loading = Loading(context)
            loading.show()
            return loading
        }
    }


    override fun getLayout(): Int {
        return R.layout.dialog_loading
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCanceledOnTouchOutside(false)
        rotate(loadingView)
    }

    override fun alpha(): Float {
        return 0f
    }

    private fun rotate(view: View): ObjectAnimator {
        val rotation = ObjectAnimator.ofFloat(view, "rotation", 0f, 359f)
        rotation.repeatCount = ObjectAnimator.INFINITE
        rotation.interpolator = LinearInterpolator()
        rotation.duration = 1500
        rotation.start()
        return rotation
    }
}