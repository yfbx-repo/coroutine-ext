package com.yfbx.coroutine

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.yfbx.coroutine.util.Loading
import com.yfbx.coroutine.util.toast
import kotlinx.coroutines.*

/**
 * Author Edward
 * Date 2019/5/24 0024
 * Description: 协程扩展，主要用来处理 Retrofit2 网络请求任务
 * 注意 本例中使用的Loading为非法的悬浮窗(无权限调用悬浮窗)，可能存在问题，建议按照需求自己实现相关扩展
 */

/**
 *  使用 lifecycleScope 进行协程任务，自动处理生命周期
 *  当生命周期 onDestroy时，协程任务 会被 cancel
 */
fun LifecycleOwner.network(block: suspend CoroutineScope.() -> Unit): Job {
    return lifecycleScope.launch(NetErrorHandler(), CoroutineStart.DEFAULT, block)
}

/**
 * 在FragmentActivity中 扩展带Loading的任务
 */
fun FragmentActivity.loading(show: Boolean = true, block: suspend CoroutineScope.() -> Unit): Job {
    val loading = if (show) Loading.show() else null
    val job = lifecycleScope.launch(NetErrorHandler(), CoroutineStart.DEFAULT, block)
    job.invokeOnCompletion { loading?.dismiss() }
    return job
}

/**
 * 异常回调
 * 可以多次调用 invokeOnCompletion，按调用顺序执行
 */
fun Job.onError(onError: (code: Int, message: String) -> Unit): Job {
    invokeOnCompletion { throwable ->
        if (throwable != null) {
            val error = throwable.error
            onError.invoke(error.code, error.msg)
        }
    }
    return this
}

/**
 * 协程异常处理，当出现异常时，协程任务自动取消
 */
@Suppress("FunctionName")
private fun NetErrorHandler(): CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
    toast(throwable.error.msg)
}

private val Throwable.error
    get() = NetError(this)
