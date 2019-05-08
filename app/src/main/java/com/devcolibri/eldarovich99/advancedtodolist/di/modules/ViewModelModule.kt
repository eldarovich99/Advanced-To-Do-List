package com.devcolibri.eldarovich99.advancedtodolist.di.modules

import android.arch.lifecycle.ViewModel
import com.devcolibri.eldarovich99.advancedtodolist.di.components.ViewModelKey
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.viewmodel.AddNoteViewModel
import com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.viewmodel.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{
    @IntoMap
    @Binds
    @ViewModelKey(ListViewModel::class)
    abstract fun listViewModel(listViewModel: ListViewModel): ViewModel
    @IntoMap
    @Binds
    @ViewModelKey(AddNoteViewModel::class)
    abstract fun addNoteViewModel(addNoteViewModel: AddNoteViewModel): ViewModel
}