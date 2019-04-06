package com.devcolibri.eldarovich99.advancedtodolist.di

import com.devcolibri.eldarovich99.advancedtodolist.ui.ListActivity
import com.devcolibri.eldarovich99.advancedtodolist.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [AppModule::class, RoomModule::class])
interface NotesComponent{

    fun inject(activity: ListActivity)
    fun inject(viewModel: ListViewModel)
}