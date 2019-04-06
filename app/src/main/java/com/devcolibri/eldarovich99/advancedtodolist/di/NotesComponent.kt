package com.devcolibri.eldarovich99.advancedtodolist.di

import com.devcolibri.eldarovich99.advancedtodolist.ui.ListActivity
import dagger.Component

@Component(modules = [AppModule::class, RoomModule::class])
interface NotesComponent{

    fun inject(activity: ListActivity)
}