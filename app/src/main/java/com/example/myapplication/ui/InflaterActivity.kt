package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_inflater.inflaterContainer1
import kotlinx.android.synthetic.main.activity_inflater.inflaterContainer2
import kotlinx.android.synthetic.main.activity_inflater.inflaterContainer3
import kotlinx.android.synthetic.main.activity_inflater.inflaterContainer4

class InflaterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inflater)

        LayoutInflater.from(this).inflate(R.layout.text_inflater, inflaterContainer1, true)


        val inflaterView2 = LayoutInflater.from(this).inflate(R.layout.text_inflater, inflaterContainer2, false)
        inflaterContainer2.addView(inflaterView2)


        val inflaterView3 = LayoutInflater.from(this).inflate(R.layout.text_inflater, LinearLayout(this), false)
        inflaterContainer3.addView(inflaterView3)

        val inflaterView4 = LayoutInflater.from(this).inflate(R.layout.text_inflater, null, false)
        inflaterContainer4.addView(inflaterView4)

        Log.i("InflaterActivity", findViewById<FrameLayout>(android.R.id.content)?.toString() ?: "null")
    }
}