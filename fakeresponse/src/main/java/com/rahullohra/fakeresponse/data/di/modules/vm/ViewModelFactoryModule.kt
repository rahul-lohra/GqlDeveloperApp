package com.rahullohra.fakeresponse.data.di.modules.vm

import androidx.lifecycle.ViewModelProvider
import com.rahullohra.fakeresponse.presentaiton.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}