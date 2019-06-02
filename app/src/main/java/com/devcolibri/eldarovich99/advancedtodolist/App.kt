package com.devcolibri.eldarovich99.advancedtodolist

import android.app.Application

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }
}