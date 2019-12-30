package com.yfbx.coroutinesdemo.net.helper

import android.util.Log
import com.yfbx.coroutinesdemo.BuildConfig
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset

/**
 * Author: Edward
 * Date: 2018/11/16
 * Description:
 */

object NetLogger {

    private val TAG = "NET"
    private val UTF8 = Charset.forName("UTF-8")

    /**
     * Request
     */
    fun log(request: Request) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "--> ${request.method()} ${request.url()}")

            val headers = request.headers()
            for (i in 0 until headers.size()) {
                Log.i(TAG, headers.name(i) + ": " + headers.value(i))
            }

            val buffer = Buffer()
            request.body()?.writeTo(buffer)
            Log.i(TAG, buffer.readString(UTF8))
            Log.i(TAG, "--> END ${request.method()}")
        }
    }


    /**
     * Response
     */
    fun log(response: Response, tookTime: Long) {
        val url = response.request().url().toString()
        Log.i(TAG, "<-- ${response.code()} ${response.message()} $url ($tookTime ms)")

        val headers = response.headers()
        for (i in 0 until headers.size()) {
            Log.i(TAG, headers.name(i) + ": " + headers.value(i))
        }

        response.body()?.let {
            val source = it.source()
            source.request(java.lang.Long.MAX_VALUE)
            val buffer = source.buffer
            Log.i(TAG, buffer.clone().readString(UTF8))
            Log.i(TAG, "<-- END HTTP (" + buffer.size() + "-byte body)")
        }
    }
}