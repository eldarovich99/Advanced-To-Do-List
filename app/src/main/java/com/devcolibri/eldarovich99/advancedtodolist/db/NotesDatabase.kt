package com.devcolibri.eldarovich99.advancedtodolist.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devcolibri.eldarovich99.advancedtodolist.db.converter.DateConverter
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.TaskDao
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task

@Database(entities = [Note::class, Task::class], version = 1)
@TypeConverters( DateConverter::class)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun tasksDao(): TaskDao
    companion object {
        @Volatile  // means that this object will not be cached
        private var INSTANCE: NotesDatabase? = null
    }
}