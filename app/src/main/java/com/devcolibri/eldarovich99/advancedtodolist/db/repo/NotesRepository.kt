package com.devcolibri.eldarovich99.advancedtodolist.db.repo

import android.support.annotation.WorkerThread
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepository @Inject constructor(val notesDao: NotesDao) {
    val allNotes: Observable<List<Note>> = notesDao.getAllNotes().toObservable()
     // called on a worker thread
    fun insert(note: Note){     // the modifier means that a function can be interrupted and then continued
        Completable.fromAction{ notesDao.insert(note) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
    @WorkerThread
    fun delete(note: Note){
        Completable.fromAction{ notesDao.delete(note) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}