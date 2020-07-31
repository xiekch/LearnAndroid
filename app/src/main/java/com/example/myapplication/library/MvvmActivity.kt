package com.example.myapplication.library

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_mvvm.*

class MvvmActivity : AppCompatActivity() {
    val TAG = "MvvmActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        val viewModel = ViewModelProvider(this).get(MvvmViewModel::class.java)
        button_change_user.setOnClickListener {
            viewModel.changeUser()
        }

        viewModel.userLiveData.observe(this, Observer {
            text_user.text = it.toString()
            Log.i(TAG, "update")
        })
    }

}