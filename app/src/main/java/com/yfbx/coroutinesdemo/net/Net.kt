package com.yfbx.coroutinesdemo.net

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.stream.MalformedJsonException
import com.yfbx.coroutinesdemo.dialog.Loading
import com.yfbx.coroutinesdemo.net.helper.CookiesKeeper
import com.yfbx.coroutinesdemo.net.helper.EmptyConverterFactory
import com.yfbx.coroutinesdemo.net.helper.NetInterceptor
import com.yfbx.coroutinesdemo.utils.toast
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Author Edward
 * Date 2019/5/24 0024
 * Description:
 */
private const val HOST = "http://www-dev.yuxiaor.com/api/v1/"

private val client = OkHttpClient.Builder()
        .cookieJar(CookiesKeeper)
        .addInterceptor(NetInterceptor())
        .build()

val Net: Retrofit = Retrofit.Builder()
        .baseUrl(HOST)
        .client(client)
        .addConverterFactory(EmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun LifecycleOwner.network(block: suspend CoroutineScope.() -> Unit): Job {
    return lifecycleScope.launch(ErrorHandler(), CoroutineStart.DEFAULT, block)
}

fun LifecycleOwner.loading(context: Activity?, block: suspend CoroutineScope.() -> Unit): Job {
    val loading = if (context != null) Loading.show(context) else null
    val job = network(block)
    job.invokeOnCompletion {
        loading?.dismiss()
    }
    return job
}

fun FragmentActivity.loading(show: Boolean = true, block: suspend CoroutineScope.() -> Unit): Job {
    val context = if (show) this else null
    return loading(context, block)
}

fun Fragment.loading(show: Boolean = true, block: suspend CoroutineScope.() -> Unit): Job {
    val context = if (show) activity else null
    return loading(context, block)
}

fun Job.onError(onError: (code: Int, msg: String) -> Unit): Job {
    invokeOnCompletion {
        if (it != null) {
            val error = handleError(it)
            onError.invoke(error.first, error.second)
        }
    }
    return this
}

@Suppress("FunctionName")
fun ErrorHandler(): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, throwable ->
        val error = handleError(throwable)
        toast(error.second)
    }
}


fun handleError(throwable: Throwable): Pair<Int, String> {
    return when (throwable) {
        is UnknownHostException -> 0 to "网络连接失败，请检查您的网络"
        is SocketTimeoutException -> 1 to "网络连接超时，请检查您的网络"
        is ConnectException -> 2 to "连接服务器失败，请稍后再试"
        is MalformedJsonException -> 3 to "当前为测试环境，请连接内部网络"
        is HttpException -> {
            val error = throwable.response()?.errorBody()?.string()
            val map = Gson().fromJson(error, Map::class.java)
            val errorCode = map["errorCode"] as Int
            val errorMessage = map["message"] as String
            errorCode to errorMessage
        }
        else -> 99 to "请求出错，请稍后再试"
    }
}