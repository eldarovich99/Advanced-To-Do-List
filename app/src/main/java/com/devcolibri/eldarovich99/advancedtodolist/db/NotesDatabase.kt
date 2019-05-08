package com.devcolibri.eldarovich99.advancedtodolist.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.devcolibri.eldarovich99.advancedtodolist.db.converter.DateConverter
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.TaskDao
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Note::class, Task::class], version = 1)
@TypeConverters( DateConverter::class)
abstract class NotesDatabase:RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun tasksDao(): TaskDao
    companion object {
        @Volatile  // means that this object will not be cached
        private var INSTANCE: NotesDatabase? = null
        fun getDatabase(context: Context, scope:CoroutineScope): NotesDatabase {
            return INSTANCE ?: synchronized(this){       // this block of code will be executed only on a single thread simultaneously
                val instance = Room.databaseBuilder(context.applicationContext,
                    NotesDatabase::class.java,
                    "Notes_database").addCallback(NoteDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }

    }

    // insert some values into database to chech if it works
    private class NoteDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.notesDao())
                }
            }
        }
        fun populateDatabase(notesDao: NotesDao) {
            //var note = Note(1, Date(),"It is a title","Hello", 1)
            //notesDao.insert(note)
            //note = Note(2, Date(),"It is a second title","World", 45)
            //notesDao.insert(note)
        }
    }
}