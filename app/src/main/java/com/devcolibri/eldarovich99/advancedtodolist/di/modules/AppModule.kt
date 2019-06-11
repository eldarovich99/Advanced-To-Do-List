package com.devcolibri.eldarovich99.advancedtodolist.di.modules

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.devcolibri.eldarovich99.advancedtodolist.db.NotesDatabase
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.NotesDao
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.TaskDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var application: Application){
    var database: NotesDatabase = Room.databaseBuilder(application, NotesDatabase::class.java, "notes-db").build()

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesNotesDatabase(application: Application) : NotesDatabase {
        return database
    }

    @Provides
    @Singleton
    fun providesNotesDao(): NotesDao {
        return database.notesDao()
    }

    @Provides
    @Singleton
    fun providesTaskDao(): TaskDao{
        return database.tasksDao()
    }
    @Provides
    @Singleton
    fun provideActivity(): FragmentActivity {
        return FragmentActivity()
    }
}