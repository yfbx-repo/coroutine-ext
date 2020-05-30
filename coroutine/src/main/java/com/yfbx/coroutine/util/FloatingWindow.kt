package com.yfbx.coroutine.util

import android.content.Context
import android.os.Build
import android.view.*
import androidx.annotation.LayoutRes
import kotlinx.android.extensions.LayoutContainer
import kotlin.properties.Delegates


/**
 * @Author: Edward
 * @Date: 2019-07-20
 * @Description:
 */
open class FloatingWindow(val context: Context) : LayoutContainer {
    override val containerView: View? by lazy { contentView }

    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val layoutParams = WindowManager.LayoutParams()
    var gravity: Int by Delegates.observable(Gravity.CENTER) { _, _, newValue ->
        layoutParams.gravity = newValue
    }
    var width: Int by Delegates.observable(WindowManager.LayoutParams.WRAP_CONTENT) { _, _, newValue ->
        layoutParams.width = newValue
    }
    var height: Int by Delegates.observable(WindowManager.LayoutParams.WRAP_CONTENT) { _, _, newValue ->
        layoutParams.height = newValue
    }
    var alpha: Float by Delegates.observable(1.0f) { _, _, newValue ->
        layoutParams.alpha = newValue
    }
    private var onDismiss: (() -> Unit)? = null
    private var contentView: View? = null
    var cancelable = true
    var cancelOnTouch = true
    var isShowing = false


    init {
        width = WindowManager.LayoutParams.WRAP_CONTENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        gravity = Gravity.CENTER
        alpha = 1.0f
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            layoutParams.type = 2037
        } else {
            layoutParams.type = 2005
        }
    }

    open fun onCreate(context: Context) {

    }


    open fun setContentView(contentView: View) {
        this.contentView = contentView
        contentView.isFocusable = true
        contentView.isFocusableInTouchMode = true
        contentView.setOnKeyListener(this::onKey)
        contentView.setOnTouchListener(this::onTouch)
    }

    open fun setContentView(@LayoutRes layoutRes: Int) {
        setContentView(LayoutInflater.from(context).inflate(layoutRes, null, false))
    }

    open fun show() {
        if (contentView == null) {
            onCreate(context)
        }

        try {
            isShowing = true
            windowManager.addView(contentView, layoutParams)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun dismiss() {
        if (isShowing) {
            isShowing = false
            onDismiss?.invoke()
            windowManager.removeViewImmediate(contentView)
        }
    }

    open fun cancel() {
        if (cancelable) {
            dismiss()
        }
    }

    open fun onDismiss(onDismiss: () -> Unit) {
        this.onDismiss = onDismiss
    }

    private fun onTouch(v: View, event: MotionEvent): Boolean {
        if (cancelOnTouch && event.action == MotionEvent.ACTION_UP) {
            cancel()
            return true
        }
        return false
    }

    private fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            cancel()
            return true
        }
        return false
    }

}