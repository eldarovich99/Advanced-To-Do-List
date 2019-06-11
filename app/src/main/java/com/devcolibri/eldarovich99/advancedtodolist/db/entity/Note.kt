package com.devcolibri.eldarovich99.advancedtodolist.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note_table")
data class Note(@PrimaryKey(autoGenerate = true) var id:Int = 0, var date:Date = Date(), var title:String = "", var text:String = "", var mood:Int = 0)