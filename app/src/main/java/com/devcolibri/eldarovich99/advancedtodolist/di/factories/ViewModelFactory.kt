package com.devcolibri.eldarovich99.advancedtodolist.di.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.NonNull
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(var viewModelMap: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory{
    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        val provider : Provider<ViewModel> = viewModelMap[modelClass] ?: viewModelMap.asIterable().firstOrNull(){
            modelClass.isAssignableFrom(it.key) }?.value
        ?: throw IllegalArgumentException("unknown model class " + modelClass)

        return try {
            provider.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}