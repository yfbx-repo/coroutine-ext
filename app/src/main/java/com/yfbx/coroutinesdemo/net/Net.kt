package com.yfbx.coroutinesdemo.net

import com.yfbx.coroutinesdemo.BuildConfig
import com.yfbx.coroutinesdemo.net.helper.CookiesKeeper
import com.yfbx.coroutinesdemo.net.helper.EmptyConverterFactory
import com.yfbx.coroutinesdemo.net.helper.HeaderInterceptor
import com.yfbx.coroutinesdemo.net.helper.LoggerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author Edward
 * Date 2019/5/24 0024
 * Description:
 */

object Net {

    private const val HOST = "http://www-dev.yuxiaor.com/api/v1/"
    val retrofit = getRetrofit(true)


    inline fun <reified T> create(): T {
        return retrofit.create(T::class.java)
    }

    /**
     * Retrofit
     */
    private fun getRetrofit(isJson: Boolean = true): Retrofit {
        val builder = Retrofit.Builder()
        builder.baseUrl(HOST)
        builder.client(client())
        if (isJson) {
            builder.addConverterFactory(EmptyConverterFactory())
            builder.addConverterFactory(GsonConverterFactory.create())
        }
        return builder.build()
    }

    /**
     * OkHttpClient
     */
    private fun client(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HeaderInterceptor())
        builder.cookieJar(CookiesKeeper)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(LoggerInterceptor())
        }
        builder.retryOnConnectionFailure(true)
        return builder.build()
    }

}