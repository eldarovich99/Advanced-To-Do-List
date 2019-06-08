package com.devcolibri.eldarovich99.advancedtodolist.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.view.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, ListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}
