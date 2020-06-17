package com.yfbx.coroutine

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
 * CoroutineScope扩展
 * Google官方推荐写法 BaseActivity 继承 CoroutineScope by MainScope()
 * 在onDestroy 时调用 CoroutineScope 的 cancel方法取消协程任务
 */
fun CoroutineScope.network(block: suspend CoroutineScope.() -> Unit): Job {
    return launch(NetErrorHandler(), CoroutineStart.DEFAULT, block)
}

/**
 * CoroutineScope扩展
 */
fun CoroutineScope.loading(showLoading: Boolean = true, block: suspend CoroutineScope.() -> Unit): Job {
    val loading = if (showLoading) Loading.show() else null
    val job = network(block)
    job.invokeOnCompletion { loading?.dismiss() }
    return job
}

fun Job.onError(onError: (code: Int, message: String) -> Unit): Job {
    invokeOnCompletion { throwable ->
        throwable?.error?.let {
            onError.invoke(it.code, it.msg)
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