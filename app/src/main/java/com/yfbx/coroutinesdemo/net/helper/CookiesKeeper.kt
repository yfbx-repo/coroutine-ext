package com.yfbx.coroutinesdemo.net.helper

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.ArrayList
import kotlin.collections.HashMap

/**
 * Author: Edward
 * Date: 2018/12/5
 * Description: to persist cookies
 */
object CookiesKeeper : CookieJar {

    private val cookieSet = HashMap<String, MutableList<Cookie>>()

    override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
        val host = url!!.host()
        if (cookies != null) {
            cookieSet[host] = cookies
        }
    }

    override fun loadForRequest(url: HttpUrl?): MutableList<Cookie> {
        return cookieSet[url!!.host()] ?: ArrayList()
    }


    /**
     * 清除Cookie
     */
    fun clear() {
        cookieSet.clear()
    }
}
