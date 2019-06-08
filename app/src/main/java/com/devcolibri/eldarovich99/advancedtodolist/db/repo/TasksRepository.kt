package com.devcolibri.eldarovich99.advancedtodolist.db.repo

import android.support.annotation.WorkerThread
import com.devcolibri.eldarovich99.advancedtodolist.db.dao.TaskDao
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepository @Inject constructor(val tasksDao: TaskDao) {
    private lateinit var allTasks: Observable<List<Task>>

    fun setAllTasks(id:Int){
        allTasks = tasksDao.getAllTasks(id).toObservable()
    }

    @WorkerThread       // called on a worker thread
    fun insert(task: Task){     // the modifier means that a function can be interrupted and then continued
        tasksDao.insert(task)
    }
    @WorkerThread
    fun delete(task: Task){
        tasksDao.delete(task)
    }
    @WorkerThread
    fun getTasks(id:Int) : Observable<List<Task>>{
        return tasksDao.getAllTasks(id).toObservable()
    }

}