package com.rahullohra.fakeresponse

import android.content.Context
import java.io.File
import java.io.FileOutputStream
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

        val libs = context.getDir("gqlLibs", Context.MODE_PRIVATE)
        if (!libs.exists()) {
            libs.mkdir()
        }
        val fileName = "sqlite3"
        val file = File(libs, fileName)
        if (file.exists()) {
            file.delete()
        }

        val fos = FileOutputStream(file)
        fos.write(byteArray)
        fos.close()

        val isReadable = file.setReadable(true,false)
        val isWriteable = file.setWritable(true,true)
        val isExecutable = file.setExecutable(true,false)
    }



    fun getNewFileName(): String {
        return Date().time.toString() + ".txt"
    }
}