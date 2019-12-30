package com.rahullohra.fakeresponse.domain.usecases

import android.os.Build
import com.rahullohra.fakeresponse.App
import com.rahullohra.fakeresponse.FileUtil
import com.rahullohra.fakeresponse.domain.repository.RemoteSqliteRepository
import javax.inject.Inject

class DownloadSqliteUseCase @Inject constructor(val repository: RemoteSqliteRepository) {

    suspend fun getSqlite() {
        val cpuType =  getCpu().first()
        val byteArray = repository.getSqlite(cpuType)
        writeSqliteResponseToFile(byteArray)
    }

    fun writeSqliteResponseToFile(byteArray: ByteArray) {
        FileUtil.writeSqliteFile(byteArray, App.INSTANCE)
    }

    fun getCpu(): Array<String> {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return arrayOf(Build.CPU_ABI2)
        }
        return Build.SUPPORTED_ABIS
    }

}