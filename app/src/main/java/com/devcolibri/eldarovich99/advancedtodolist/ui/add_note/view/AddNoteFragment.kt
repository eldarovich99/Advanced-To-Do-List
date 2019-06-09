package com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devcolibri.eldarovich99.advancedtodolist.Injector
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.di.factories.ViewModelFactory
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.adapter.TaskListAdapter
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.viewmodel.AddNoteViewModel
import com.devcolibri.eldarovich99.advancedtodolist.utils.Mood
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add_note.*
import java.util.*
import javax.inject.Inject
import kotlin.math.absoluteValue

const val TITLE = "com.devcolibri.eldarovich99.advancedtodolist.ui.TITLE"
const val DATE = "com.devcolibri.eldarovich99.advancedtodolist.ui.DATE"
const val TEXT = "com.devcolibri.eldarovich99.advancedtodolist.ui.TEXT"
const val MOOD = "com.devcolibri.eldarovich99.advancedtodolist.ui.MOOD"
const val ID = "com.devcolibri.eldarovich99.advancedtodolist.ui.ID"

class AddNoteFragment : Fragment() {
    @Inject lateinit var listViewModel: AddNoteViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var adapter: TaskListAdapter
    private lateinit var disposable: Disposable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    companion object {
        fun newInstance(id:Int) : AddNoteFragment{
            val bundle = Bundle()
            bundle.putInt(ID,id)
            val fragment = AddNoteFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
        Injector.getAppComponent().inject(this)
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(listViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        button_save.setOnClickListener {
            val title = edit_title.text.toString()
            val date = Date()
            val text = edit_note.text.toString()
            val mood = when (radio_group_mood.checkedRadioButtonId){
                radio_button_bad.id -> Mood.BAD
                radio_button_average.id -> Mood.AVERAGE
                radio_button_nice.id -> Mood.NICE
                else -> Mood.NONE
            }
            val note = Note(0, date, title, text, mood.ordinal.absoluteValue) // Maybe I should encapsulate this
            // logic of adding mood by providing converter for Room to convert Mood into Int
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init(){
        val id = arguments?.get(ID) as Int
        disposable = listViewModel.allTasks
            .subscribeOn(Schedulers.io())
            .doOnNext { tasks-> tasks.let {
                Log.d("Fragment", tasks.size.toString())
            }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Log.d("FragmentAdapter", it.size.toString())
                adapter.setTasks(it)
            }
        listViewModel.init(id)
        adapter = TaskListAdapter(object: IAddTaskListener{
            override fun addTask() {
                //listViewModel.allTasks.onNext(listViewModel.allTasks.onNext(Task()))
            }
        })
        tasks_recycler_view.adapter = adapter
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}

interface IAddTaskListener{
    fun addTask()
}