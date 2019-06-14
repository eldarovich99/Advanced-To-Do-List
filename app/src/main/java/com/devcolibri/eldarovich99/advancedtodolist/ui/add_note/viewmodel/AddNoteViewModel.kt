package com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.NotesRepository
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.TasksRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(application: Application,
                                           private var tasksRepository: TasksRepository,
                                           private var notesRepository: NotesRepository):
    ViewModel(){

    var allTasks = BehaviorSubject.create<MutableList<Task>>()
    private lateinit var note: Note
    private val compositeDisposable = CompositeDisposable()

    fun init(id: Int) {
        if (id != 0) {
            val disposable = notesRepository.getNote(id)
                .doOnNext { note = it }
                .doOnComplete { setTasks(id) }
                .subscribe()
            compositeDisposable.add(disposable)
        }
        else{
            allTasks.onNext(mutableListOf(Task()))
        }
    }

    fun setTasks(id: Int){
         val disposable = tasksRepository.getTasks(note.id)
                    .subscribeOn(Schedulers.io())
                    .doOnNext {
                        if (it.isEmpty())
                            allTasks.onNext(mutableListOf(Task()))
                        else
                            allTasks.onNext(it.toMutableList())
                    }
                    //.observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        compositeDisposable.add(disposable)
    }

    fun insert(note: Note){
        val disposable = notesRepository.insert(note)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}