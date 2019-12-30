package com.yfbx.coroutinesdemo.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.yfbx.coroutinesdemo.R
import com.yfbx.coroutinesdemo.bean.User
import com.yfbx.coroutinesdemo.net.api.LoginApi
import com.yfbx.coroutinesdemo.net.net
import com.yfbx.coroutinesdemo.net.network
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import retrofit2.create


class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        infoTxt.movementMethod = ScrollingMovementMethod()
        btn1.setOnClickListener { login() }
        btn2.setOnClickListener { serialize() }
        btn3.setOnClickListener { parallel() }
    }

    private fun login() = network {
        val user = net.create<LoginApi>().login("18888888888", "123123", "123123", 1)
        infoTxt.append("单个请求：" + user.access_token)
    }


    /**
     * 串行
     */
    private fun serialize() = network {
        val user1 = net.create<LoginApi>().login("18888888888", "123123", "123123", 1)
        val user2 = net.create<LoginApi>().login("18888888888", "123123", "123123", 1)
        val user3 = net.create<LoginApi>().login("18888888888", "123123", "123123", 1)

        updateUI(user1, user2, user3)
    }

    /**
     * 并行
     */
    private fun parallel() = network {
        val user1 = async { net.create<LoginApi>().login("18888888888", "123123", "123123", 1) }
        val user2 = async { net.create<LoginApi>().login("18888888888", "123123", "123123", 1) }
        val user3 = async { net.create<LoginApi>().login("18888888888", "123123", "123123", 1) }

        updateUI(user1.await(), user2.await(), user3.await())
    }


    private fun updateUI(user1: User, user2: User, user3: User) {
        infoTxt.append("\nuser1：\n" + user1.access_token)
        infoTxt.append("\nuser2：\n" + user2.access_token)
        infoTxt.append("\nuser3：\n" + user3.access_token)

        MainScope().network {

        }
    }


}
