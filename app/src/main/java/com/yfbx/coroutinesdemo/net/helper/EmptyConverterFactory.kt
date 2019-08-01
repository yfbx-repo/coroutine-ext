package com.yfbx.coroutinesdemo.net.helper

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Author: Edward
 * Date: 2019/06/15
 * Description: to avoid Gson Parser parses Null value
 */
class EmptyConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        val delegate = retrofit?.nextResponseBodyConverter<Any>(this, type!!, annotations!!)
        return Converter<ResponseBody, Any> { if (it.contentLength() == 0L) null else delegate?.convert(it) }
    }
}