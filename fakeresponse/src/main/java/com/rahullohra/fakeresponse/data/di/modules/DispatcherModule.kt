package com.rahullohra.fakeresponse.data.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
class DispatcherModule {

    @Provides
    @Named(WORKER)
    fun provideWorkerDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named(UI)
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    companion object {
        const val WORKER = "WORKER"
        const val UI = "UI"
    }
}