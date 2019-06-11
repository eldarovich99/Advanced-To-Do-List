package com.devcolibri.eldarovich99.advancedtodolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.view.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.backStackEntryCount == 0)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ListFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}
