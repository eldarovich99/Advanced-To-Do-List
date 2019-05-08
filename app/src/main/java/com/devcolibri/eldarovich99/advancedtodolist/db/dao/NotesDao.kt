package com.devcolibri.eldarovich99.advancedtodolist.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM note_table ORDER BY date DESC")
    fun getAllNotes(): LiveData<List<Note>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)
    @Delete
    fun delete(note: Note)
}