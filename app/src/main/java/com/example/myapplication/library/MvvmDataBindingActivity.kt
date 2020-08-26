package com.example.myapplication.library

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMvvmDataBindingBinding

class MvvmDataBindingActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MvvmDataBindingActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_data_binding)
        val viewModel = ViewModelProvider(this).get(MvvmDataBindingViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityMvvmDataBindingBinding>(
                this,
                R.layout.activity_mvvm_data_binding
            )
        binding.data = viewModel
        binding.lifecycleOwner = this
    }

}