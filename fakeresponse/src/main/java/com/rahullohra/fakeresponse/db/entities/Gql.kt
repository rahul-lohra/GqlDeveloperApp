package com.rahullohra.fakeresponse.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Gql")
data class Gql(
    @PrimaryKey(autoGenerate = true) var id: Int? = 0,
    val file: String? = null,
    val createdAt: Long,
    val updatedAt: Long,
    val enabled: Boolean,
    val gqlOperationName: String,
    val javaQueryName: String?
)