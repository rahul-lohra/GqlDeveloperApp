package com.rahullohra.fakeresponse.domain.usecases

import android.text.TextUtils
import com.rahullohra.fakeresponse.data.models.Either
import com.rahullohra.fakeresponse.db.entities.FakeGql
import com.rahullohra.fakeresponse.domain.exceptions.NoGqlNameException
import com.rahullohra.fakeresponse.domain.exceptions.NoResponseException
import com.rahullohra.fakeresponse.domain.repository.LocalRepository
import com.rahullohra.fakeresponse.presentaiton.viewmodels.data.AddGqlData
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class AddToDbUseCase(val repository: LocalRepository) : BaseUseCase<LocalRepository>(repository) {

    suspend fun addToDb(data: AddGqlData): Long {
        if (TextUtils.isEmpty(data.gqlQueryName)) throw NoGqlNameException()
        if (TextUtils.isEmpty(data.response)) throw NoResponseException()
        val either = isJSONValid(data.response!!)
        return when (either) {
            is Either.Left -> throw either.a
            else -> repository.addToDb(gqlDataToGql(data))
        }
    }


    protected fun gqlDataToGql(data: AddGqlData): FakeGql {
        val date = Date()

        val gql = FakeGql(
            gqlOperationName = data.gqlQueryName!!,
            javaQueryName = data.javaQueryName,
            createdAt = date.time,
            updatedAt = date.time,
            enabled = false,
            customTag = data.customTag,
            response = data.response!!

        )
        return gql
    }
}