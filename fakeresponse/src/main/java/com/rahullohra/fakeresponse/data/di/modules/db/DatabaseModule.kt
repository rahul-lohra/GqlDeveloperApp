package com.rahullohra.fakeresponse.data.di.modules.db

import android.content.Context
import com.rahullohra.fakeresponse.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }
}