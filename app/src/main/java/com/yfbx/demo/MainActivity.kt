package com.yfbx.demo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.yfbx.coroutine.loading
import com.yfbx.coroutine.network
import com.yfbx.coroutine.onError
import com.yfbx.demo.net.Net
import com.yfbx.demo.net.UserApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.create


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        infoTxt.movementMethod = ScrollingMovementMethod()
        btn.setOnClickListener { login() }
    }

    private fun login() = network {
        val user = Net.create<UserApi>().login("18888888888", "123456")
        //UI
        infoTxt.append("Response：${user}")
    }

    /**
     * network with loading
     */
    private fun login2() {
        loading {
            val user = Net.create<UserApi>().login("18888888888", "123456")
            //UI
            infoTxt.append("Response：${user}")
        }.onError { code, msg ->
            //error
            infoTxt.append("code：$code , message:$msg")
        }.invokeOnCompletion {
            // on complete
        }
    }
}
