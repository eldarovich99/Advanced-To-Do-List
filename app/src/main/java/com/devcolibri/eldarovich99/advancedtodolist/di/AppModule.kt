package com.devcolibri.eldarovich99.advancedtodolist.di

import android.app.Application
import dagger.Module
import dagger.Provides


// Provides application context
//@Module
//class AppModule(var application: Application){
//
//    @Singleton
//    fun provideApplication(app: Application){
//        application = app
//    }
//
//    /*@Provides
//    fun getNotesRepository():NotesRepository{
//        return NotesRepository()
//    }*/
//}

@Module
class AppModule{
    @Provides
    fun provideApplication() : Application{
        return Application()
    }

    /*@Provides
    fun getNotesRepository():NotesRepository{
        return NotesRepository()
    }*/
}