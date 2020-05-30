package  com.yfbx.demo.net

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Author: Edward
 * Date: 2018/11/16
 * Description:
 */

class HeaderInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.method(request.method(), request.body())
        builder.url(request.url())
        builder.headers(headers())
        return chain.proceed(builder.build())
    }

    /**
     * 请求头
     */
    private fun headers(): Headers {
        val headers = Headers.Builder()
        headers.add("token", "xxxxxxxxx")
        return headers.build()
    }
}