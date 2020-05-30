package com.yfbx.demo.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: Edward
 * Date: 2020-05-30
 * Description:
 */
private const val HOST = "http://www-dev.yuxiaor.com/api/v1/"

private val client = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(LoggerInterceptor())
        .build()

val Net: Retrofit = Retrofit.Builder()
        .baseUrl(HOST)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
