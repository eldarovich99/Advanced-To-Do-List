package com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.NotesRepository
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.TasksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(application: Application,
                                           private var tasksRepository: TasksRepository,
                                           private var notesRepository: NotesRepository):
    ViewModel(){

    //var allTasks: Observable<List<Task>> = Observable.just(emptyList()) // It is necessary to create observable here because fragment subscribes for this
    var allTasks = PublishSubject.create<List<Task>>()
    private lateinit var note: Note
    private val compositeDisposable = CompositeDisposable()

    fun init(id: Int){
        if (id == 0) {
            note = Note()
            val disposable = notesRepository.insert(note).doOnComplete{ onNoteAcquired(id)}.subscribe()
            compositeDisposable.add(disposable)
        }
        else{
            val disposable =  notesRepository.getNote(id).doOnComplete{ onNoteAcquired(id)}.subscribe()
            compositeDisposable.add(disposable)
        }
    }

    fun onNoteAcquired(id: Int){
        val disposable = notesRepository.getNote(id)
            .subscribeOn(Schedulers.io())
            .doOnComplete {
                insertAndUpdateTasks()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun insertAndUpdateTasks(){
        val disposable = tasksRepository.insert(Task(note.id))
            .doOnComplete{
                //allTasks.onNext(tasksRepository.getTasks(note.id))
                tasksRepository.getTasks(note.id)
                    .subscribeOn(Schedulers.io())
                    .doOnNext { allTasks.onNext(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                Log.d("addNoteVM", allTasks.toString())
            }
            .subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}