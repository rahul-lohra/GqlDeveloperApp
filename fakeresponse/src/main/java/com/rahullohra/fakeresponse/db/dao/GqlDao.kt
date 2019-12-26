package com.rahullohra.fakeresponse.db.dao

import androidx.room.*
import com.rahullohra.fakeresponse.db.entities.Gql


@Dao
interface GqlDao {

    @Query("Select * FROM Gql")
    fun getAll(): List<Gql>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGql(gql: Gql): Long

    @Update
    fun updateGql(gql: Gql)
}