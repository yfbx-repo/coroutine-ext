package com.yfbx.coroutine.util

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import com.yfbx.coroutine.R

/**
 * @Author: Edward
 * @Date: 2019-07-20
 * @Description:
 */
class Loading : FloatingWindow(AppProvider.context) {

    companion object {
        fun show(): Loading {
            val loading = Loading()
            loading.show()
            return loading
        }
    }

    override fun onCreate(context: Context) {
        alpha = 0.8f

        val imageView = ImageView(context)
        imageView.setImageResource(R.drawable.ic_loading)

        val layout = LinearLayout(context)
        layout.setPadding(24.dp, 24.dp, 24.dp, 24.dp)
        layout.gravity = Gravity.CENTER
        layout.setBackgroundResource(R.drawable.bg_loading)
        layout.addView(imageView)

        setContentView(layout)
        startAnim(imageView)
    }

    private fun startAnim(view: View): ObjectAnimator {
        val rotation = ObjectAnimator.ofFloat(view, "rotation", 0f, 359f)
        rotation.repeatCount = ObjectAnimator.INFINITE
        rotation.interpolator = LinearInterpolator()
        rotation.duration = 1000
        rotation.start()
        return rotation
    }


    private val Int.dp
        get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, toFloat(), Resources.getSystem().displayMetrics).toInt()

}