package com.devcolibri.eldarovich99.advancedtodolist.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Task(@PrimaryKey(autoGenerate = true) var id:Int, var text:String, var date:Date, var note: Int)