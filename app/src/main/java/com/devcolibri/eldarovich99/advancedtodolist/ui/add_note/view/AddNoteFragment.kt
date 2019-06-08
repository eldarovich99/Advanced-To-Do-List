package com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devcolibri.eldarovich99.advancedtodolist.Injector
import com.devcolibri.eldarovich99.advancedtodolist.R
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
        fun getInstance(id:Int) : AddNoteFragment{
            val bundle = Bundle()
            bundle.putInt(ID,id)
            val fragment = AddNoteFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.getAppComponent().inject(this)
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(listViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        button_save.setOnClickListener {
            //val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_title.text)) {
                //activity!!.setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = edit_title.text.toString()
                val date = Date()
                val text = edit_note.text.toString()
                val mood = when (radio_group_mood.checkedRadioButtonId){
                    radio_button_bad.id -> Mood.BAD
                    radio_button_average.id -> Mood.AVERAGE
                    radio_button_nice.id -> Mood.NICE
                    else -> Mood.NONE
                }
//                replyIntent.putExtra(TITLE, title)
//                replyIntent.putExtra(DATE, date.time)
//                replyIntent.putExtra(TEXT, text)
//                replyIntent.putExtra(MOOD, mood)
                //activity!!.setResult(Activity.RESULT_OK, replyIntent)
            }
            //activity!!.finish()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init(){

        val id = arguments?.get(ID) as Int
        listViewModel.init(id)
        adapter = TaskListAdapter()
        tasks_recycler_view.adapter = adapter
        disposable = listViewModel.allTasks
            .subscribeOn(Schedulers.io())
            .doOnNext { tasks-> tasks.let {
                    adapter.setTasks(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}