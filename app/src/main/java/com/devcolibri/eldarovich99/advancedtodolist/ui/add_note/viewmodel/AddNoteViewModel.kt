package com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.NotesRepository
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.TasksRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(application: Application,
                                           private var tasksRepository: TasksRepository,
                                           private var notesRepository: NotesRepository):
    ViewModel(){

    lateinit var allTasks: Observable<List<Task>>
    private lateinit var note: Note
    private lateinit var disposable: Disposable

    fun init(){
        note = Note()
        notesRepository.insert(note)
        disposable = notesRepository.allNotes
            .subscribeOn(Schedulers.io())
            .doOnNext {
                tasksRepository.insert(Task(note.id))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun init(id: Int){

    }

//    fun insert(task: Task) = scope.launch(Dispatchers.IO){
//        tasksRepository.insert(task)
//    }
//
//    fun delete(task: Task) = scope.launch(Dispatchers.IO) {
//        tasksRepository.delete(task)
//    }
//
//    fun insert(note: Note) = scope.launch(Dispatchers.IO){
//        notesRepository.insert(note)
//    }
//
//    fun delete(note: Note) = scope.launch(Dispatchers.IO) {
//        notesRepository.delete(note)
//    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}