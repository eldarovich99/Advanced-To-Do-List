package com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import com.devcolibri.eldarovich99.advancedtodolist.Injector
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.di.factories.ViewModelFactory
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.adapter.TaskListAdapter
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.viewmodel.AddNoteViewModel
import com.devcolibri.eldarovich99.advancedtodolist.utils.Mood
import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*
import javax.inject.Inject

class AddNoteActivity : AppCompatActivity() {
    @Inject lateinit var listViewModel: AddNoteViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var adapter: TaskListAdapter

    companion object {
        const val TITLE = "com.devcolibri.eldarovich99.advancedtodolist.ui.TITLE"
        const val DATE = "com.devcolibri.eldarovich99.advancedtodolist.ui.DATE"
        const val TEXT = "com.devcolibri.eldarovich99.advancedtodolist.ui.TEXT"
        const val MOOD = "com.devcolibri.eldarovich99.advancedtodolist.ui.MOOD"
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        init()
        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_title.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
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
                replyIntent.putExtra(TITLE, title)
                replyIntent.putExtra(DATE, date.time)
                replyIntent.putExtra(TEXT, text)
                replyIntent.putExtra(MOOD, mood)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
    fun init(){
        Injector.getAppComponent().inject(this)
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(listViewModel::class.java)
        adapter = TaskListAdapter(this)
        tasks_recycler_view.adapter = adapter
        listViewModel.allTasks.observe(this, Observer { tasks->
            tasks?.let { adapter.setTasks(it) }
        })
    }

}