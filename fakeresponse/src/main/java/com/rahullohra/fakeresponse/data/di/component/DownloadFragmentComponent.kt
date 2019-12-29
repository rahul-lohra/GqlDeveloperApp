package com.rahullohra.fakeresponse.data.di.component

import com.rahullohra.fakeresponse.data.di.modules.DispatcherModule
import com.rahullohra.fakeresponse.data.di.modules.vm.DownloadViewModelModule
import com.rahullohra.fakeresponse.data.di.scopes.DownloadFragmentScope
import com.rahullohra.fakeresponse.presentaiton.fragments.DownloadFragment
import dagger.Component

@DownloadFragmentScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DispatcherModule::class, DownloadViewModelModule::class]
)
interface DownloadFragmentComponent {
    fun inject(fragment: DownloadFragment)
}