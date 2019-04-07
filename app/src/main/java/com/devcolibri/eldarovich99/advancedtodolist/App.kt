package com.devcolibri.eldarovich99.advancedtodolist

import android.app.Application

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}