package com.yfbx.demo.net

import com.yfbx.demo.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: Edward
 * Date: 2020-05-30
 * Description:
 */
private const val HOST = "http://xxx.com/"

private val client = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(LoggerInterceptor())
            }
        }
        .build()

val Net: Retrofit = Retrofit.Builder()
        .baseUrl(HOST)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
