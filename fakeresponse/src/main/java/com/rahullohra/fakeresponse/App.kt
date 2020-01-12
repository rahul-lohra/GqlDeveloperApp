package com.rahullohra.fakeresponse

import android.app.Application
import com.rahullohra.fakeresponse.data.di.component.AppComponent
import com.rahullohra.fakeresponse.data.di.component.DaggerAppComponent
import com.rahullohra.fakeresponse.data.di.modules.AppContextModule

open class App : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        appComponent = DaggerAppComponent.builder()
            .appContextModule(AppContextModule(this))
            .build()
        appComponent.inject(this)
    }


}