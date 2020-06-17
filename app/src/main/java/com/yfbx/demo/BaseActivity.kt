package com.yfbx.demo

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Author: Edward
 * Date: 2020-06-17
 * Description:
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}