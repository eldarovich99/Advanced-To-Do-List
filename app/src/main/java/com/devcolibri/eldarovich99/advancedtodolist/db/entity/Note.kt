package com.devcolibri.eldarovich99.advancedtodolist.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "note_table")
data class Note(@PrimaryKey(autoGenerate = true) val id:Int, var date:Date, var title:String, var text:String, var mood:Int)