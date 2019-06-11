package com.devcolibri.eldarovich99.advancedtodolist.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks_table")
data class Task(@PrimaryKey(autoGenerate = true) var id:Int = 0, var text:String = "", var date:Date = Date(), var note: Int = 0)