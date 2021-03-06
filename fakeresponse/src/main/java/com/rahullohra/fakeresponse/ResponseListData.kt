package com.rahullohra.fakeresponse

data class ResponseListData(
    val id: Int,
    val title: String,
    var isChecked: Boolean,
    val customName: String? = null,
    val responseType:ResponseItemType
)

enum class ResponseItemType { GQL, REST }