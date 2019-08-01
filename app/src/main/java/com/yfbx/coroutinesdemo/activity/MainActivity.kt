package com.yfbx.coroutinesdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yfbx.coroutinesdemo.R
import com.yfbx.coroutinesdemo.net.Net
import com.yfbx.coroutinesdemo.net.api.LoginApi
import com.yfbx.coroutinesdemo.net.async
import com.yfbx.coroutinesdemo.net.load
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener { login1() }
        btn2.setOnClickListener { login2() }
        btn3.setOnClickListener { }
    }

    private fun login1() {
        launch {
            val user = Net.create<LoginApi>().login("18888888888", "123123", "123123", 1).load()
            infoTxt.text = user?.access_token
        }
    }


    private fun login2() {
        launch {
            val user = Net.create<LoginApi>().login("18888888888", "123123", "123123", 1).async()
            infoTxt.text = user?.access_token
        }
    }


}
