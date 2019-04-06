package com.devcolibri.eldarovich99.advancedtodolist.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.devcolibri.eldarovich99.advancedtodolist.db.NotesRepository
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application, private var noteDao: NotesDao, private var repository: NotesRepository): AndroidViewModel(application) { //If you need the application context, use AndroidViewModel, as shown in this codelab.
    //@Inject lateinit var repository: NotesRepository
    val allNotes: LiveData<List<Note>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        allNotes = repository.allNotes
    }

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