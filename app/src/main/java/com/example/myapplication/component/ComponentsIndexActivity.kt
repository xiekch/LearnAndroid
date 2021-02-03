package com.example.myapplication.component

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_components_index.*

class ComponentsIndexActivity : AppCompatActivity(), View.OnClickListener {
    private val myReceiver = MyReceiver()
    private val actionName = "com.example.broadcast"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_components_index)
        button_service.setOnClickListener(this)
        button_activity.setOnClickListener(this)
        button_broadcast_receiver.setOnClickListener(this)
        registerReceiver(myReceiver, IntentFilter(actionName))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_service -> {
                startActivity(Intent(this, ServiceIndexActivity::class.java))
            }
            R.id.button_activity -> {
                startActivity(Intent(this, LifeCycleActivity::class.java))
            }
            R.id.button_broadcast_receiver -> sendBroadcast(Intent("com.example.broadcast"))
        }
    }
}