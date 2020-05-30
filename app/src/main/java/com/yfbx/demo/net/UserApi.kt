package com.yfbx.demo.net

import com.yfbx.demo.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Author: Edward
 * Date: 2019/06/25
 * Description:
 */
interface UserApi {

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(@Field("account") mobile: String, @Field("password") password: String): User

}