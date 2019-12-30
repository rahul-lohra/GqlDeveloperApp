package com.rahullohra.fakeresponse.data.di.component

import com.rahullohra.fakeresponse.data.di.modules.DispatcherModule
import com.rahullohra.fakeresponse.data.di.modules.db.DaoModule
import com.rahullohra.fakeresponse.data.di.modules.vm.FakeResponseViewModelModule
import com.rahullohra.fakeresponse.data.di.scopes.FakeResponseFragmentScope
import com.rahullohra.fakeresponse.presentaiton.fragments.FakeResponseFragment
import dagger.Component

@FakeResponseFragmentScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DispatcherModule::class, FakeResponseViewModelModule::class, DaoModule::class]
)
interface FakeResponseFragmentComponent {
    fun inject(fragment: FakeResponseFragment)
}