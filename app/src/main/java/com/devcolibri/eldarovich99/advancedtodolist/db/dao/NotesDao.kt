package com.devcolibri.eldarovich99.advancedtodolist.db.dao

import android.arch.persistence.room.*
import android.support.annotation.WorkerThread
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import io.reactivex.Flowable

@Dao
interface NotesDao {
    @WorkerThread
    @Query("SELECT * FROM note_table ORDER BY date DESC")
    fun getAllNotes(): Flowable<List<Note>>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @WorkerThread
    @Delete
    fun delete(note: Note)

    @WorkerThread
    @Query("SELECT * FROM note_table WHERE id = :id")
    fun getNote(id: Int): Flowable<Note>
}