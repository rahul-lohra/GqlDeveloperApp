package com.rahullohra.fakeresponse.data.di.modules.db

import com.rahullohra.fakeresponse.db.AppDatabase
import com.rahullohra.fakeresponse.db.dao.GqlDao
import dagger.Module
import dagger.Provides

@Module
class DaoModule {

    @Provides
    fun provideDao(db: AppDatabase): GqlDao {
        return db.gqlDao()
    }

}