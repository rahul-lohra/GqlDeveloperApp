package com.rahullohra.fakeresponse.domain.repository

import java.net.URL
import javax.inject.Inject

class RemoteSqliteRepository @Inject constructor(){

    companion object AbiEndpoints {
        const val BASE_URL = "https://raw.githubusercontent.com/"
        const val DIRECTORY_URL = "${BASE_URL}EXALAB/sqlite3-android/master/binary/"
        const val sqlite3 = "/sqlite3"
        const val x86 = "${DIRECTORY_URL}/x86${sqlite3}"
    }

    fun getSqlite(): ByteArray {
        return URL(x86).readBytes()
    }
}