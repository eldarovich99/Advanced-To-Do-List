package com.devcolibri.eldarovich99.advancedtodolist.di.components

import com.devcolibri.eldarovich99.advancedtodolist.di.annotations.ActivityScope
import com.devcolibri.eldarovich99.advancedtodolist.ui.ListActivity
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface NotesComponent{
    fun inject(activity: ListActivity)
}