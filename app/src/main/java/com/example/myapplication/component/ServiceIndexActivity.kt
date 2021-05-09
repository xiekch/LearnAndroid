package com.example.myapplication.component

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class ServiceIndexActivity : AppCompatActivity(), View.OnClickListener, ServiceConnection {
    private var mService: MyService? = null
    private var isBound = false

    companion object {
        private const val TAG = "ServiceIndexActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_index)

        findViewById<Button>(R.id.button_start_service).setOnClickListener(this)
        findViewById<Button>(R.id.button_stop_service).setOnClickListener(this)
        findViewById<Button>(R.id.button_bind_service).setOnClickListener(this)
        findViewById<Button>(R.id.button_unbind_service).setOnClickListener(this)
        findViewById<Button>(R.id.button_intent_service).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_start_service ->
                startService(Intent(this, MyService::class.java))

            R.id.button_stop_service ->
                stopService(Intent(this, MyService::class.java))

            R.id.button_bind_service ->
                bindService(
                    Intent(this, MyService::class.java),
                    this,
                    Context.BIND_AUTO_CREATE
                )

            R.id.button_unbind_service -> {
                unbindService(this)
                isBound = false
                mService = null
            }

            R.id.button_intent_service -> {
                Log.i("MainThread", "Thread id: " + Thread.currentThread().id)
                startService(Intent(this, MyIntentService::class.java))
            }
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.i(TAG, "service connected")
        isBound = true
        mService = (service as MyService.MyBinder).getService()
        mService?.startDownload()
        mService?.getProgress()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.i(TAG, "service disconnected")
        isBound = false
        mService = null
    }
}