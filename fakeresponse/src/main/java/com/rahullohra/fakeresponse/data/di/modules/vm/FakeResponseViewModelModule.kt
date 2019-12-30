package com.rahullohra.fakeresponse.data.di.modules.vm

import androidx.lifecycle.ViewModel
import com.rahullohra.fakeresponse.presentaiton.viewmodels.DownloadFragmentVM
import com.rahullohra.fakeresponse.presentaiton.viewmodels.FakeResponseModel
import com.rahullohra.fakeresponse.presentaiton.viewmodels.FakeResponseVM
import com.rahullohra.fakeresponse.presentaiton.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FakeResponseViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FakeResponseVM::class)
    internal abstract fun provideFakeResponseVM(viewModel: FakeResponseVM): ViewModel
}