package com.devcolibri.eldarovich99.advancedtodolist.db.repo

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
    fun getNotes(): Observable<List<Note>>{
        return notesDao.getAllNotes().toObservable()
    }

    fun insert(note: Note): Completable{
        return Completable.fromAction{ notesDao.insert(note) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun delete(note: Note) : Completable{
        return Completable.fromAction{ notesDao.delete(note) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getNote(id: Int) : Observable<Note>{
        return notesDao.getNote(id).subscribeOn(Schedulers.io()).toObservable()
    }

    fun update(note: Note) : Completable {
        return Completable.fromAction {
            notesDao.update(note)
        }
            .subscribeOn(Schedulers.io())
    }
}