package com.example.myapplication.component

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Broadcast received", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Broadcast received")
    }

    companion object {
        const val TAG = "MyReceiver"
    }
}