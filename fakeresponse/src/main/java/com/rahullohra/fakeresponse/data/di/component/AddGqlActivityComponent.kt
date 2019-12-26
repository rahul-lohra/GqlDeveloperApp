package com.rahullohra.fakeresponse.data.di.component

import com.rahullohra.fakeresponse.data.di.modules.DispatcherModule
import com.rahullohra.fakeresponse.data.di.modules.db.DaoModule
import com.rahullohra.fakeresponse.data.di.modules.repository.RepositoryModule
import com.rahullohra.fakeresponse.data.di.modules.vm.ViewModelModule
import com.rahullohra.fakeresponse.data.di.scopes.ActivityScope
import com.rahullohra.fakeresponse.presentaiton.activities.AddGqlActivity
import dagger.Component


@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DispatcherModule::class, RepositoryModule::class, ViewModelModule::class, DaoModule::class]
)
interface AddGqlActivityComponent {
    fun inject(AddGqlActivity: AddGqlActivity)
}