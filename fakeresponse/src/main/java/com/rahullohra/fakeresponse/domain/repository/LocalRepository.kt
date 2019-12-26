package com.rahullohra.fakeresponse.domain.repository

import com.rahullohra.fakeresponse.db.dao.GqlDao
import com.rahullohra.fakeresponse.db.entities.Gql
import javax.inject.Inject

class LocalRepository @Inject constructor(val dao: GqlDao) {

    suspend fun addToDb(gql: Gql): Long {
        return dao.insertGql(gql)
    }

    suspend fun getAllGql(): List<Gql> {
        return dao.getAll()
    }
}