package com.rahullohra.fakeresponse.data.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}

