package com.rahullohra.fakeresponse.db.dao

import androidx.room.*
import com.rahullohra.fakeresponse.db.entities.FakeGql


@Dao
interface GqlDao {

    @Query("Select * FROM FakeGql")
    fun getAll(): List<FakeGql>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGql(fakeGql: FakeGql): Long

    @Update
    fun updateGql(fakeGql: FakeGql)

    @Query("DELETE from FakeGql")
    fun deleteAll()
}