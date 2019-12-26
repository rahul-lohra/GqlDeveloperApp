package com.rahullohra.fakeresponse.data.di.modules.vm

import androidx.lifecycle.ViewModel
import com.rahullohra.fakeresponse.presentaiton.viewmodels.FakeResponseModel
import com.rahullohra.fakeresponse.presentaiton.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FakeResponseModel::class)
    internal abstract fun targetPromotionsDialogVM(viewModel: FakeResponseModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(FakeResponseModel::class)
//    internal abstract fun targetPromotionsDialogVM(viewModel: FakeResponseModel): ViewModel

}