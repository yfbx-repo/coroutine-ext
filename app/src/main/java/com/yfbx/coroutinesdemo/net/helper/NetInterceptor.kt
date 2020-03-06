package com.yfbx.coroutinesdemo.net.helper

import com.yfbx.coroutinesdemo.BuildConfig
import okhttp3.*

/**
 * Author: Edward
 * Date: 2018/11/16
 * Description:
 */

class NetInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = buildRequest(chain.request())
        NetLogger.log(request)
        try {
            val startMs = System.currentTimeMillis()
            val response = chain.proceed(request)
            val tookTime = System.currentTimeMillis() - startMs
            NetLogger.log(response, tookTime)
            return response
        } catch (e: Throwable) {
            e.printStackTrace()
            return buildResponse(request, e)
        }
    }


    private fun buildRequest(request: Request): Request {
        return request.newBuilder()
                .method(request.method(), request.body())
                .url(request.url())
                .headers(headers())
                .build()
    }

    private fun buildResponse(request: Request, e: Throwable): Response {
        return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(0)
                .message(e.message ?: "未知错误")
                .body(ResponseBody.create(MediaType.parse("json:application/json"), "{}"))
                .build()
    }

    /**
     * Headers
     */
    private fun headers(): Headers {
        val headers = Headers.Builder()
        headers.add("User-Agent", userAgent())
        headers.add("Accept-Language", "zh-CN")
        return headers.build()
    }


    /**
     * UserAgent
     */
    private fun userAgent(): String {
        return "${BuildConfig.FLAVOR}/${BuildConfig.VERSION_NAME} (${BuildConfig.APPLICATION_ID}; build:${BuildConfig.VERSION_CODE}; Android)"
    }
}