package com.rahullohra.fakeresponse.data.interceptor

import android.content.Context
import android.text.TextUtils
import com.rahullohra.fakeresponse.data.parsers.GqlRequestBodyParser
import com.rahullohra.fakeresponse.db.AppDatabase
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import org.json.JSONArray
import org.json.JSONObject

class GqlTestingInterceptor(context: Context) : Interceptor {

    val dao = AppDatabase.getDatabase(context).gqlDao()
    val requestParser = GqlRequestBodyParser(dao)

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val request = chain.request()
        val buffer = Buffer()
        request.body?.writeTo(buffer)
        val requestBody = buffer.readUtf8()
        if (isGqlRequest(request)) {
            if (!TextUtils.isEmpty(requestBody)) {
                val fakeResponse = requestParser.parse(requestBody, response.body?.string())
                if (!TextUtils.isEmpty(fakeResponse)) {
                    return createResponseFromFakeResponse(fakeResponse!!, request)
                }
            }
        }
        return response
    }

    fun createResponseFromFakeResponse(fakeResponse: String, request: Request): Response {
        val formattedResponse = getFormmatedResponseForGql(fakeResponse)
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        return Response.Builder()
            .code(200)
            .message(fakeResponse)
            .request(request)
            .body(ResponseBody.create(mediaType, formattedResponse))
            .build()
    }

    fun getFormmatedResponseForGql(fakeResponse: String): String {
        val array = JSONArray()
        val firstObject = JSONObject()
        firstObject.put("data", fakeResponse)
        array.put(firstObject)
        return array.toString()
    }

    fun isGqlRequest(request: Request): Boolean {
        return true
    }

}