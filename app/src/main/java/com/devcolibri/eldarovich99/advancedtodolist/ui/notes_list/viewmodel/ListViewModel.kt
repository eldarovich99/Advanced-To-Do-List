package com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.viewmodel

import androidx.lifecycle.ViewModel
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.NotesRepository
import io.reactivex.Observable
import javax.inject.Inject

class ListViewModel @Inject constructor(private var repository: NotesRepository):
    ViewModel() { //If you need the application context, use AndroidViewModel.
    val allNotes: Observable<List<Note>> = repository.getNotes()

    fun insert(note:Note){
        repository.insert(note)
    }

    fun delete(note:Note){
        repository.delete(note)
    }

    override fun onCleared() {
        super.onCleared()
    }
}