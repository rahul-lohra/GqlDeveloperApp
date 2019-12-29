package com.rahullohra.fakeresponse

import android.content.Context
import java.io.File
import java.util.*

object FileUtil {

    const val PARENT = "responses"
    fun createParent(): File {
        val parent = File(PARENT)
        if (!parent.exists()) {
            parent.mkdirs()
        }
        return parent
    }

    fun createFile(prefix: String): File {
        val fileName = prefix + "_" + getNewFileName()
        val f = File(PARENT, fileName)
        f.mkdirs()
        return f
    }

    fun writeToFile(fileContents: String, prefix: String, context: Context) {
        val fileName = prefix + "_" + getNewFileName()
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }

    fun writeSqliteFile(byteArray: ByteArray, context: Context) {

        val libs = context.getDir("libs",Context.MODE_PRIVATE)
        if(!libs.exists()){
            libs.mkdir()
        }
        val fileName = "sqlite"
        context.openFileOutput(libs.name+"/"+fileName, Context.MODE_PRIVATE).use {
            it.write(byteArray)
        }
    }

    fun getNewFileName(): String {
        return Date().time.toString() + ".txt"
    }
}