package com.yfbx.coroutinesdemo.net.api

import com.yfbx.coroutinesdemo.bean.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Author: Edward
 * Date: 2019/06/25
 * Description:
 */
interface LoginApi {


    @FormUrlEncoded
    @POST("sys/login")
    suspend fun login(@Field("mobile") mobile: String,
                      @Field("code") code: String,
                      @Field("password") password: String,
                      @Field("type") type: Int): User

}