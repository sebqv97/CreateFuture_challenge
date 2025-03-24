package com.createfuture.takehome

import android.app.Application
import com.createfuture.takehome.di.AppModule
import com.createfuture.takehome.di.AppModuleImpl

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //load modules that will be singletons
        appModule = AppModuleImpl()
    }

    internal companion object {
        lateinit var appModule: AppModule
        var
    }
}
