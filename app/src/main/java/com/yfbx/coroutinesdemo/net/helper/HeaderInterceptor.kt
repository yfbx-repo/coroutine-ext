package com.yfbx.coroutinesdemo.net.helper

import com.yfbx.coroutinesdemo.BuildConfig
import com.yfbx.coroutinesdemo.Prefs
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Author: Edward
 * Date: 2018/11/16
 * Description:
 */

class HeaderInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.method(request.method(), request.body())
        builder.url(request.url())
        builder.headers(headers())
        return chain.proceed(builder.build())
    }

    /**
     * Headers
     */
    private fun headers(): Headers {
        val headers = Headers.Builder()
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
        headers.add("XXX-YUXIAOR-TOKEN", Prefs.getToken())
        headers.add("User-Agent", userAgent())
        headers.add("Accept-Language", "zh-CN")
        return headers.build()
    }


    /**
     * UserAgent
     */
    private fun userAgent(): String {
        return "${BuildConfig.FLAVOR}/${BuildConfig.VERSION_NAME} (${BuildConfig.APPLICATION_ID}; build:${BuildConfig.VERSION_CODE}; Android)"
    }
}