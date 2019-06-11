package com.devcolibri.eldarovich99.advancedtodolist.db.dao

import androidx.room.*
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task
import io.reactivex.Flowable

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task:Task)
    @Delete
    fun delete(task: Task)
    @Query("SELECT * FROM tasks_table")
    fun getAllTasks() : Flowable<List<Task>>
    @Query("SELECT * FROM tasks_table WHERE note = :id")
    fun getAllTasks(id:Int) : Flowable<List<Task>>
}