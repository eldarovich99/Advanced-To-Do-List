package com.devcolibri.eldarovich99.advancedtodolist.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks_table")
data class Task(@PrimaryKey(autoGenerate = true) var id:Int = 0, var text:String = "", var date:Date = Date(), var note: Int = 0)