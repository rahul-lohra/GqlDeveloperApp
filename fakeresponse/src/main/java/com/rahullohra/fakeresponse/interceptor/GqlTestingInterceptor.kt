package com.rahullohra.fakeresponse.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class GqlTestingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val method = original.method
        val requestBuilder = original.newBuilder()
        val request = requestBuilder.build()
        if (isGqlRequest(request)) {

        } else {
            return chain.proceed(request)
        }
        return chain.proceed(request)
    }

    fun isGqlRequest(request: Request):Boolean{
        return true
    }

}