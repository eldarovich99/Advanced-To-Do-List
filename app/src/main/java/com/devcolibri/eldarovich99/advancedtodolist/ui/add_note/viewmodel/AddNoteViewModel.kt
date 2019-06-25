package com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.NotesRepository
import com.devcolibri.eldarovich99.advancedtodolist.db.repo.TasksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(val application: Application,
                                           private var tasksRepository: TasksRepository,
                                           private var notesRepository: NotesRepository):
    ViewModel(){

    var allTasks = BehaviorSubject.create<MutableList<Task>>()
    var note = BehaviorSubject.create<Note>()
    private val compositeDisposable = CompositeDisposable()

    fun init(id: Int) {
        if (id != 0) {
            val disposable = notesRepository.getNote(id)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { note.onNext(it) }
                .doOnComplete { setTasks(id) }
                .subscribe()
            compositeDisposable.add(disposable)
        }
        else{
            allTasks.onNext(mutableListOf(Task()))
        }
    }

    fun setTasks(id: Int){
         val disposable = tasksRepository.getTasks(note.value.id)
                    .subscribeOn(Schedulers.io())
                    .doOnNext {
                        if (it.isEmpty())
                            allTasks.onNext(mutableListOf(Task()))
                        else
                            allTasks.onNext(it.toMutableList())
                    }
                    .subscribe()
        compositeDisposable.add(disposable)
    }

    fun setNote(note: Note){
        //edit_title..
    }

    fun insert(note: Note){
        val disposable = notesRepository.insert(note)
            .subscribe()
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun update(note: Note) {
        notesRepository.update(note).subscribe()
    }
}