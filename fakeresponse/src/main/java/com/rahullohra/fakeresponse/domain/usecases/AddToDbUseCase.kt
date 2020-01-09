package com.rahullohra.fakeresponse.domain.usecases

import android.text.TextUtils
import com.rahullohra.fakeresponse.App
import com.rahullohra.fakeresponse.FileUtil
import com.rahullohra.fakeresponse.db.entities.FakeGql
import com.rahullohra.fakeresponse.domain.repository.LocalRepository
import com.rahullohra.fakeresponse.presentaiton.viewmodels.data.AddGqlData
import java.io.File
import java.util.*
import javax.inject.Inject

class AddToDbUseCase @Inject constructor(val repository: LocalRepository) {

    suspend fun getFileName(prefix: String): File {
        return FileUtil.createFile(prefix)
    }

    suspend fun addToDb(data: AddGqlData): Long {
        var filePrefix: String? = data.gqlQueryName
        if (TextUtils.isEmpty(filePrefix)) {
            filePrefix = data.customTag
        }
        if (TextUtils.isEmpty(filePrefix)) throw NoGqlNameException()
        if (TextUtils.isEmpty(data.response)) throw NoResponseException()

        writeResponseToFile(data.response!!, filePrefix!!)
        return repository.addToDb(gqlDataToGql(data))
    }

    suspend fun writeResponseToFile(response: String, filePrefix: String) {
        FileUtil.writeToFile(response, filePrefix, App.INSTANCE)
    }

    protected fun gqlDataToGql(data: AddGqlData): FakeGql {
        val date = Date()

        val gql = FakeGql(
            gqlOperationName = data.gqlQueryName!!,
            javaQueryName = data.javaQueryName!!,
            createdAt = date.time,
            updatedAt = date.time,
            enabled = false,
            customTag = data.customTag

        )
        return gql
    }

    class NoGqlNameException : Exception() {
        override val message: String?
            get() = "Either query name or custom name is empty"
    }

    class NoResponseException : Exception() {
        override val message: String?
            get() = "response is empty"
    }
}