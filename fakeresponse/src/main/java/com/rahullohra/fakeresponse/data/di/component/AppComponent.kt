package com.rahullohra.fakeresponse.data.di.component

import android.content.Context
import com.rahullohra.fakeresponse.App
import com.rahullohra.fakeresponse.data.di.modules.AppContextModule
import com.rahullohra.fakeresponse.data.di.modules.db.DatabaseModule
import com.rahullohra.fakeresponse.data.di.modules.vm.ViewModelFactoryModule
import com.rahullohra.fakeresponse.data.di.scopes.AppScope
import com.rahullohra.fakeresponse.db.AppDatabase
import com.rahullohra.fakeresponse.db.dao.GqlDao
import dagger.Component

@AppScope
@Component(
    modules = [AppContextModule::class,
        DatabaseModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent {
    fun provideContext(): Context
    fun provideAppDatabse(): AppDatabase
    fun inject(app: App)
}