package com.rahullohra.fakeresponse.domain.repository

import com.rahullohra.fakeresponse.db.dao.GqlDao
import com.rahullohra.fakeresponse.db.entities.FakeGql
import javax.inject.Inject

class LocalRepository @Inject constructor(val dao: GqlDao) {

    suspend fun addToDb(fakeGql: FakeGql): Long {
        return dao.insertGql(fakeGql)
    }

    suspend fun getAllGql(): List<FakeGql> {
        return dao.getAll()
    }

    suspend fun deleteAllRecords(){
        return dao.deleteAll()
    }
}