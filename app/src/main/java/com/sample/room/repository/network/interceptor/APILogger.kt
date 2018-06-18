package com.sample.room.repository.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

/**
 * Created by Anurag Garg on 16-02-2017.
 */
class APILogger : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        // generate the request
        val request = chain.request()

        // print the request
        Timber.d("**********************       REQUEST INITATED     **********************")
        Timber.d("REQUEST URL -> %s", request.url())
        Timber.d("REQUEST HEADERS -> %s", request.headers())
        Timber.d("**********************       REQUEST FINISHED     **********************")

        // generate the response
        val response = chain.proceed(chain.request())

        // print the response
        Timber.d("**********************       RESPONSE INITIATED     **********************")
        Timber.d("RESPONSE CODE -> %s", response.code())
        Timber.d("RESPONSE HEADERS -> %s", response.headers())
        Timber.d("**********************       RESPONSE FINISHED     **********************")

        // returning the response object
        return response
    }
}