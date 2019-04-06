package com.devcolibri.eldarovich99.advancedtodolist.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{
    @Provides
    @Singleton
    fun provideApplication() : Application{
        return Application()
    }
}