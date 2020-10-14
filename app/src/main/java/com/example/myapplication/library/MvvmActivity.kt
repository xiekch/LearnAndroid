package com.example.myapplication.library

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_mvvm.button_add
import kotlinx.android.synthetic.main.activity_mvvm.counter

class MvvmActivity : AppCompatActivity() {
    private val viewModel by viewModels<MvvmViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        button_add.setOnClickListener { viewModel.add() }
        button_add.postDelayed({
            viewModel.getNumber().observe(this, Observer {
                counter.text = it.toString()
            })
        }, 1000)
    }

    companion object {
        const val TAG = "MvvmActivity"
    }
}