package com.rahullohra.fakeresponse.domain.usecases

import com.rahullohra.fakeresponse.domain.repository.LocalRepository
import javax.inject.Inject

class UpdateGqlUseCase @Inject constructor(val localRepository: LocalRepository) {

    suspend fun toggleGql(gqlRecord: Int, enable: Boolean) {
        localRepository.toggleGqlRecord(gqlRecord, enable)
    }

    suspend fun deleteAllRecords() {
        localRepository.deleteAllRecords()
    }
}