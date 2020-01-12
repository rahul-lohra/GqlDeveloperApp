package com.rahullohra.fakeresponse.domain.repository

import com.rahullohra.fakeresponse.db.dao.GqlDao
import com.rahullohra.fakeresponse.db.entities.FakeGql

class LocalRepository (val dao: GqlDao) :BaseRepository{

    suspend fun addToDb(fakeGql: FakeGql): Long {
        return dao.insertGql(fakeGql)
    }

    suspend fun getAllGql(): List<FakeGql> {
        return dao.getAll()
    }

    suspend fun deleteAllRecords() {
        return dao.deleteAll()
    }

    suspend fun toggleGqlRecord(gqlRecord: Int, enable: Boolean) {
        return dao.toggleGql(gqlRecord, enable)
    }
}