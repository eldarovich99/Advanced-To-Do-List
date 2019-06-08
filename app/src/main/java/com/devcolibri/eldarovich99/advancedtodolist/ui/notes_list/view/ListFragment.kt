package com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devcolibri.eldarovich99.advancedtodolist.Injector
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.di.factories.ViewModelFactory
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.view.AddNoteFragment
import com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.adapter.NoteListAdapter
import com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.viewmodel.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_notes_feed.*
import javax.inject.Inject

class ListFragment : Fragment() {
    @Inject lateinit var listViewModel: ListViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var adapter: NoteListAdapter
    private lateinit var disposable: Disposable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes_feed, container, false)
    }

    companion object {
        fun newInstance(): ListFragment{
            return ListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.getAppComponent().inject(this)
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(listViewModel:: class.java)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = NoteListAdapter()
        recycler.adapter = adapter
        disposable = listViewModel.allNotes
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { tasks-> tasks.let { adapter.setNotes(it) } }
            .subscribe()

        add_image_button.setOnClickListener{
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddNoteFragment.getInstance(0))
                .addToBackStack(null)
                .commit()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            data.let {
//                val note = Note(0,Date(data!!.getLongExtra(DATE, -1)),
//                    data.getStringExtra(TITLE), data.getStringExtra(
//                        TEXT),
//                    data.getIntExtra(MOOD, Mood.NONE.ordinal))
//                //wordViewModel.insert(word)
//                listViewModel.insert(note)
//            }
//        } else {
//            Toast.makeText(
//                context,
//                R.string.empty_not_saved,
//                Toast.LENGTH_LONG).show()
//        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
