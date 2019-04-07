package com.devcolibri.eldarovich99.advancedtodolist

import android.app.Application
import com.devcolibri.eldarovich99.advancedtodolist.di.components.AppComponent
import com.devcolibri.eldarovich99.advancedtodolist.di.components.DaggerAppComponent
import com.devcolibri.eldarovich99.advancedtodolist.di.components.NotesComponent
import com.devcolibri.eldarovich99.advancedtodolist.di.modules.RoomModule

object Injector {
    private var notesComponent: NotesComponent? = null
    private var appComponent: AppComponent? = null

    fun init(application: Application) {
        appComponent = DaggerAppComponent.builder().roomModule(RoomModule(application)).build()
    }

    fun getNotesComponent(): NotesComponent {
        if (notesComponent == null) {
            notesComponent = appComponent!!.plusNoteComponent()
        }
        return notesComponent!!
    }

    fun destroyPersonComponent() {
        notesComponent = null
    }

}