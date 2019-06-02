package com.devcolibri.eldarovich99.advancedtodolist.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.devcolibri.eldarovich99.advancedtodolist.db.converter.DateConverter
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.TaskDao
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task

@Database(entities = [Note::class, Task::class], version = 1)
@TypeConverters( DateConverter::class)
abstract class NotesDatabase:RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun tasksDao(): TaskDao
    companion object {
        @Volatile  // means that this object will not be cached
        private var INSTANCE: NotesDatabase? = null
    }
}