package com.devcolibri.eldarovich99.advancedtodolist.db

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import javax.inject.Inject

class NotesRepository @Inject constructor(val notesDao: NotesDao) {
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    @WorkerThread       // called on a worker thread
    fun insert(note: Note){     // the modifier means that a function can be interrupted and then continued
        notesDao.insert(note)
    }
    @WorkerThread
    fun delete(note: Note){
        notesDao.delete(note)
    }
}