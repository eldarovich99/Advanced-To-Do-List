package com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ListViewModel @Inject constructor(application: Application, private var noteDao: NotesDao, private var repository: NotesRepository):
    ViewModel() { //If you need the application context, use AndroidViewModel.
    val allNotes: LiveData<List<Note>> = repository.allNotes
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun insert(note:Note) = scope.launch(Dispatchers.IO){
        repository.insert(note)
    }

    fun delete(note:Note) = scope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}