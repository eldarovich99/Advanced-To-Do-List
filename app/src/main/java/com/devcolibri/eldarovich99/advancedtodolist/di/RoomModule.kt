package com.devcolibri.eldarovich99.advancedtodolist.di

import android.app.Application
import android.arch.persistence.room.Room
import com.devcolibri.eldarovich99.advancedtodolist.db.NotesDatabase
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import dagger.Module
import dagger.Provides

@Module
class RoomModule(application: Application) {
    var database: NotesDatabase = Room.databaseBuilder(application, NotesDatabase::class.java, "notes-db").build()

    @Provides
    fun providesNotesDatabase(): NotesDatabase{
        return database
    }

    @Provides
    fun providesNotesDao(): NotesDao{
        return database.notesDao()
    }
}