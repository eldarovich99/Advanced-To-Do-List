package com.devcolibri.eldarovich99.advancedtodolist.di.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.devcolibri.eldarovich99.advancedtodolist.viewmodel.ListViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(var listViewModel: Provider<ListViewModel>): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return listViewModel.get() as T
    }

}