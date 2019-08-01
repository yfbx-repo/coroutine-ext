package com.yfbx.coroutinesdemo

import android.content.Context
import com.yfbx.coroutinesdemo.global.App.Companion.app

/**
 * Author: Edward
 * Date: 2019/06/24
 * Description:
 */
object Prefs {

    private val prefs = app.getSharedPreferences("share_data", Context.MODE_PRIVATE)


    /**
     * Token
     */
    fun saveToken(token: String?) {
        prefs.edit().putString("KEY_TOKEN", token).apply()
    }

    fun getToken(): String {
        return prefs.getString("KEY_TOKEN", "") ?: ""
    }

    fun clearToken() {
        prefs.edit().remove("KEY_TOKEN").apply()
    }

}