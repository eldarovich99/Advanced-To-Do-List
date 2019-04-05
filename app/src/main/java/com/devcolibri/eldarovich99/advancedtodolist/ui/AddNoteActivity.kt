package com.devcolibri.eldarovich99.advancedtodolist.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import com.devcolibri.eldarovich99.advancedtodolist.R
import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*


class AddNoteActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

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

    companion object {
        const val TITLE = "com.devcolibri.eldarovich99.advancedtodolist.ui.TITLE"
        const val DATE = "com.devcolibri.eldarovich99.advancedtodolist.ui.DATE"
        const val TEXT = "com.devcolibri.eldarovich99.advancedtodolist.ui.TEXT"
        const val MOOD = "com.devcolibri.eldarovich99.advancedtodolist.ui.MOOD"
    }
}