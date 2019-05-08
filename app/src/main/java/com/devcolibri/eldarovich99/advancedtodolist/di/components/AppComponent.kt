package com.devcolibri.eldarovich99.advancedtodolist.di.components

import com.devcolibri.eldarovich99.advancedtodolist.di.modules.AppModule
import com.devcolibri.eldarovich99.advancedtodolist.di.modules.ViewModelModule
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.view.AddNoteActivity
import com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.view.ListActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, ViewModelModule::class])
@Singleton
interface AppComponent{
    fun inject(activity: ListActivity)
    fun inject(activity: AddNoteActivity)
}