package com.devcolibri.eldarovich99.advancedtodolist.services

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.devcolibri.eldarovich99.advancedtodolist.R
import com.devcolibri.eldarovich99.advancedtodolist.ui.add_note.AddNoteActivity
import com.devcolibri.eldarovich99.advancedtodolist.ui.notes_list.view.ListActivity

class DelayedMessageService : IntentService {
    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
    constructor(): super("Delayed_service")
    override fun onHandleIntent(intent: Intent?) {
        synchronized(this){
            try{
                Thread.sleep(10000)
            }
            catch (e:InterruptedException){}
        }
        val text = intent?.getStringExtra(EXTRA_MESSAGE)
        showText(text)
    }
    private fun showText(text:String?){
        Log.v("Delay", "this is a message $text")
        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.abc_ic_search_api_material)
            .setContentTitle(getString(R.string.question))
            .setContentText(getString(R.string.continue_adding))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0,200))
            .setAutoCancel(true)
        val actionIntent = Intent(this, AddNoteActivity::class.java) // it will be used to open activity from notification
        val pendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_ONE_SHOT)
        builder.setContentIntent(pendingIntent)
        with(NotificationManagerCompat.from(this)){
            notify(ListActivity.NOTIFICATION_ID, builder.build())
        }
    }
}