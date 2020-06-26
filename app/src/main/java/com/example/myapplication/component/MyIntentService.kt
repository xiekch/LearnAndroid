package com.example.myapplication.component

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
class MyIntentService : IntentService("MyIntentService") {
    override fun onCreate() {
        super.onCreate()
        Log.i("MyIntentService", "onCreate Thread id: " + Thread.currentThread().id)
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i("MyIntentService", "onHandleIntent Thread id: " + Thread.currentThread().id)
        Thread.sleep(1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyIntentService", "onDestroy Thread id: " + Thread.currentThread().id)
    }
}
