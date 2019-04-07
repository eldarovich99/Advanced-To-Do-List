package com.devcolibri.eldarovich99.advancedtodolist

import android.app.Application
import com.devcolibri.eldarovich99.advancedtodolist.di.components.AppComponent
import com.devcolibri.eldarovich99.advancedtodolist.di.components.DaggerAppComponent
import com.devcolibri.eldarovich99.advancedtodolist.di.modules.AppModule

object Injector {
    private var appComponent: AppComponent? = null

    fun init(application: Application) {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent!!
    }
}