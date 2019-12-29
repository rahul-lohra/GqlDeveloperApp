package com.rahullohra.fakeresponse.domain.usecases

import com.rahullohra.fakeresponse.App
import com.rahullohra.fakeresponse.FileUtil
import com.rahullohra.fakeresponse.domain.repository.RemoteSqliteRepository
import javax.inject.Inject

class DownloadSqliteUseCase @Inject constructor(val repository: RemoteSqliteRepository){

    suspend fun getSqlite(){
        val byteArray = repository.getSqlite()
        writeSqliteResponseToFile(byteArray)
    }

    fun writeSqliteResponseToFile(byteArray: ByteArray){
        FileUtil.writeSqliteFile(byteArray, App.INSTANCE)
    }

}