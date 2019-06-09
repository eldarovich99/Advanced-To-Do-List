package com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.db.entity.Task
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.view.IAddTaskListener

class TaskListAdapter internal constructor(val listener: IAddTaskListener
) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {
    private var tasks = emptyList<Task>() // Cached copy of tasks

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.task_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.task_item, parent, false)
        itemView.setOnClickListener{
            listener.addTask()
        }
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = tasks[position]
        holder.wordItemView.text = current.text
    }

    internal fun setTasks(tasks: List<Task>) {
        Log.d("adapter:", tasks.size.toString())
        this.tasks = tasks
        notifyDataSetChanged()
    }

    override fun getItemCount() = tasks.size

    fun getTaskAtPosition(position: Int): Task {
        return tasks[position]
    }
}