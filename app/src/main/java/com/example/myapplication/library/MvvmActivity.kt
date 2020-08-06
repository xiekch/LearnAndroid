package com.example.myapplication.library

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMvvmBinding
import kotlinx.android.synthetic.main.activity_mvvm.*

class MvvmActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MvvmActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        val viewModel = ViewModelProvider(this).get(MvvmViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityMvvmBinding>(this, R.layout.activity_mvvm)
        binding.data = viewModel
        binding.lifecycleOwner = this
    }

}