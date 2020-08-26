package com.example.myapplication.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_mvvm.button_add
import kotlinx.android.synthetic.main.activity_mvvm.counter

class MvvmActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MvvmActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        val viewModel = ViewModelProvider(this).get(MvvmViewModel::class.java)
        button_add.setOnClickListener { viewModel.add() }
        viewModel.getNumber().observe(this, Observer {
            counter.text = it.toString()
        })
    }

}