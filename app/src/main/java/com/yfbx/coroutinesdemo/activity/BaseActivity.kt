package com.yfbx.coroutinesdemo.activity

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Author: Edward
 * Date: 2019-12-20
 * Description:
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    override fun onDestroy() {
        super.onDestroy()
        cancel() // CoroutineScope.cancel
    }
}