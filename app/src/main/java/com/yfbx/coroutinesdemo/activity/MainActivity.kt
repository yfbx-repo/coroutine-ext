package com.yfbx.coroutinesdemo.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.yfbx.coroutinesdemo.R
import com.yfbx.coroutinesdemo.api.LoginApi
import com.yfbx.coroutinesdemo.net.Net
import com.yfbx.coroutinesdemo.net.loading
import com.yfbx.coroutinesdemo.net.onError
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.create


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        infoTxt.movementMethod = ScrollingMovementMethod()
        btn1.setOnClickListener { login() }
    }

    private fun login() = loading {
        val user = Net.create<LoginApi>().login("18888888888", "123123", "123123", 1)
        infoTxt.append("单个请求：" + user.access_token)
    }


    private fun login2() {
        loading {
            val user = Net.create<LoginApi>().login("18888888888", "123123", "123123", 1)
            infoTxt.append("单个请求：" + user.access_token)
        }.onError { code, msg ->
            println(msg)
        }.invokeOnCompletion {
            //
        }
    }
}
