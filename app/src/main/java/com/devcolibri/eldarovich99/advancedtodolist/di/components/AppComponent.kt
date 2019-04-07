package com.devcolibri.eldarovich99.advancedtodolist.di.components

import com.devcolibri.eldarovich99.advancedtodolist.di.modules.AppModule
import com.devcolibri.eldarovich99.advancedtodolist.ui.ListActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent{
    fun inject(activity:ListActivity)
}