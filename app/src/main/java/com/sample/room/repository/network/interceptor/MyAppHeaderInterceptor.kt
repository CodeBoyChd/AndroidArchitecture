package com.databuddy.app.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Created by Anurag Garg on 16-02-2017.
 */
class MyAppHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        // generate the original request
        val originalRequest = chain.request()

        // generate modified request object
        // build the modified request on the original request
        val requestBuilder: Request.Builder? = originalRequest.newBuilder()
                .header("content-type", "application/x-www-form-urlencoded")
                .method(originalRequest.method(), originalRequest.body())

        // generate the final request
        val request = requestBuilder!!.build()

        // return with the modified request
        return chain.proceed(request)
    }
}
