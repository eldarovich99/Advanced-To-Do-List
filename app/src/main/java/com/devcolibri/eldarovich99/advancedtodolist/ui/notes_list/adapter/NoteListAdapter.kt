package com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Note

class NoteListAdapter(
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
    private var notes = emptyList<Note>() // Cached copy of notes

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.wordItemView.text = current.title
    }

    internal fun setNotes(words: List<Note>) {
        this.notes = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = notes.size

    fun getNoteAtPosition(position: Int):Note{
        return notes[position]
    }
}