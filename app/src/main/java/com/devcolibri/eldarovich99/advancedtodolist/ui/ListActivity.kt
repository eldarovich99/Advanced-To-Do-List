package com.devcolibri.eldarovich99.advancedtodolist.ui

//import com.devcolibri.eldarovich99.advancedtodolist.di.components.DaggerNotesComponent
import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note
import com.devcolibri.eldarovich99.advancedtodolist.di.components.DaggerAppComponent
import com.devcolibri.eldarovich99.advancedtodolist.di.modules.RoomModule
import com.devcolibri.eldarovich99.advancedtodolist.services.DelayedMessageService
import com.devcolibri.eldarovich99.advancedtodolist.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class ListActivity : AppCompatActivity() {
    @Inject lateinit var listViewModel: ListViewModel
    private lateinit var adapter:NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val notesComponent = DaggerNotesComponent.builder().application(application).build()
        val appComponent = DaggerAppComponent.builder().roomModule(RoomModule(application)).build()
        val notesComponent = appComponent.plusNoteComponent()
        notesComponent.inject(this)

        adapter = NoteListAdapter(applicationContext)
        recycler.adapter = adapter
        listViewModel.allNotes.observe(this, Observer { notes->
            notes?.let { adapter.setNotes(it) }
        })
        add_image_button.setOnClickListener{
            val intent = Intent(this@ListActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        val simpleItemTouchCallback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(recycler: RecyclerView, holder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(holder: RecyclerView.ViewHolder, swipeDirection: Int) {
                val position = holder.adapterPosition
                val note = adapter.getNoteAtPosition(position)
                listViewModel.delete(note)
                adapter.notifyItemRemoved(position)
            }
        }
        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(recycler)
    }

    companion object {
        const val newWordActivityRequestCode = 1
        const val NOTIFICATION_ID = 5394
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val note = Note(Date(data.getLongExtra(AddNoteActivity.DATE, -1)),
                    data.getStringExtra(AddNoteActivity.TITLE), data.getStringExtra(AddNoteActivity.TEXT),
                    data.getIntExtra(AddNoteActivity.MOOD, Mood.NONE.ordinal))
                //wordViewModel.insert(word)
                listViewModel.insert(note)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
            // launch sample service
            val anotherIntent = Intent(this, DelayedMessageService::class.java)
            anotherIntent.putExtra(DelayedMessageService.EXTRA_MESSAGE, "let's add some notes!")
            startService(anotherIntent)
        }
    }
}
