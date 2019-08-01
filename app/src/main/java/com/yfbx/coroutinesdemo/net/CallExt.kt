package com.yfbx.coroutinesdemo.net

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.yfbx.coroutinesdemo.dialog.Loading
import com.yfbx.coroutinesdemo.global.ext.showError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

/**
 * Author: Edward
 * Date: 2019-08-01
 * Description:
 */

/**
 * async with loading
 */
suspend fun <T> Call<T>.load(showLoading: Boolean = true, onError: ((ErrorMsg) -> Unit)? = null): T? {
    val loading = if (showLoading) Loading() else null
    loading?.show()
    val response = withContext(Dispatchers.IO) { doExecute(onError) }
    loading?.dismiss()
    return response
}


/**
 * async
 */
suspend fun <T> Call<T>.async(onError: ((ErrorMsg) -> Unit)? = null): T? {
    return withContext(Dispatchers.IO) { doExecute(onError) }
}


/**
 * sync
 */
fun <T> Call<T>.doExecute(onError: ((ErrorMsg) -> Unit)? = null): T? {
    try {
        val response = execute()
        if (!response.isSuccessful) {
            throw Throwable(response.errorBody()?.string())
        }
        return response.body()
    } catch (t: Throwable) {
        t.printStackTrace()
        handleError(t.message, onError)
    }
    return null
}

/**
 * error
 */
fun handleError(message: String?, onError: ((ErrorMsg) -> Unit)?) {
    val error = when {
        message == null -> ErrorMsg("0", "Connection Failed!")
        message.startsWith("{") -> Gson().fromJson<ErrorMsg>(message, ErrorMsg::class.java)
        else -> ErrorMsg("0", message)
    }
    Handler(Looper.getMainLooper()).post {
        if (onError != null) {
            onError.invoke(error)
        } else {
            showError(error.message)
        }
    }
}

/**
 * Error Msg
 */
data class ErrorMsg(val errorCode: String, val message: String)