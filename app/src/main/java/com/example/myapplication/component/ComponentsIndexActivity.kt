package com.example.myapplication.component

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class ComponentsIndexActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_components_index)
        val buttonservice = findViewById<Button>(R.id.button_service)
        buttonservice.setOnClickListener(this)
        findViewById<Button>(R.id.button_activity).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_service -> {
                startActivity(Intent(this, ServiceIndexActivity::class.java))
            }
            R.id.button_activity -> {
                startActivity(Intent(this, LifeCycleActivity::class.java))
            }
        }
    }
}