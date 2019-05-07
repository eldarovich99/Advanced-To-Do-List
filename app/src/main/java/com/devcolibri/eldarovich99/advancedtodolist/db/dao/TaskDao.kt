package com.devcolibri.eldarovich99.advancedtodolist.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task:Task)
    @Delete
    fun delete(task: Task)
    @Query("SELECT * FROM task")
    fun getAllTasks() : LiveData<List<Task>>
}