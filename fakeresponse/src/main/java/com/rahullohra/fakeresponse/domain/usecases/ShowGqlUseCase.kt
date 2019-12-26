package com.rahullohra.fakeresponse.domain.usecases

import com.rahullohra.fakeresponse.ResponseListData
import com.rahullohra.fakeresponse.domain.repository.LocalRepository
import javax.inject.Inject

class ShowGqlUseCase @Inject constructor(val localRepository: LocalRepository) {

    suspend fun getAllQueries(): List<ResponseListData> {
        return localRepository.getAllGql()
            .map { ResponseListData(it.id!!, it.gqlOperationName, it.enabled) }
    }
}