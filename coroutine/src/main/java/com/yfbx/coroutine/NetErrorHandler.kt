package com.yfbx.coroutine

import com.google.gson.Gson
import com.google.gson.stream.MalformedJsonException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Author: Edward
 * Date: 2020-06-17
 * Description: 异常处理
 */

internal class NetError(throwable: Throwable) {
    var code = 0
    var msg = ""

    init {
        if (throwable is HttpException) {
            parse(throwable)
        } else {
            handleError(throwable)
        }
    }

    private fun parse(exception: HttpException) {
        val response = exception.response() ?: return
        val error = response.errorBody()?.string()
        val map = Gson().fromJson(error, Map::class.java)
        code = map["errorCode"] as? Int ?: 0
        msg = map["message"] as? String ?: ""
    }

    private fun handleError(throwable: Throwable) {
        code = 0
        msg = when (throwable) {
            is UnknownHostException -> "未知的主机地址！"
            is SocketTimeoutException -> "网络连接超时！"
            is ConnectException -> "连接服务器失败！"
            is MalformedJsonException -> "当前为测试环境，请连接内部网络"
            else -> throwable.message ?: "未知错误！"
        }
    }
}