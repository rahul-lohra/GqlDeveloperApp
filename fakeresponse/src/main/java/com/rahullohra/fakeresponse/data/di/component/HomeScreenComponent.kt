package com.rahullohra.fakeresponse.data.di.component

import com.rahullohra.fakeresponse.data.di.modules.DispatcherModule
import com.rahullohra.fakeresponse.data.di.modules.db.DaoModule
import com.rahullohra.fakeresponse.data.di.modules.repository.RepositoryModule
import com.rahullohra.fakeresponse.data.di.modules.vm.ViewModelModule
import com.rahullohra.fakeresponse.data.di.scopes.HomeScreenScope
import com.rahullohra.fakeresponse.presentaiton.fragments.FakeResponseFragment
import com.rahullohra.fakeresponse.presentaiton.fragments.GqlFragment
import dagger.Component

@HomeScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DispatcherModule::class, RepositoryModule::class, DaoModule::class, ViewModelModule::class]
)
interface HomeScreenComponent {
    fun inject(fakeResponseFragment: GqlFragment)
}