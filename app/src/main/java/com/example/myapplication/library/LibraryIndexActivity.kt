package com.example.myapplication.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_library_index.*

class LibraryIndexActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_index)
        button_mvvm.setOnClickListener(this)
        button_okHttp.setOnClickListener(this)
        button_retrofit.setOnClickListener(this)
        button_rxJava.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_mvvm -> startActivity(Intent(this, MvvmActivity::class.java))
            R.id.button_okHttp -> OkHttpActivity.actionStart(this)
            R.id.button_rxJava -> startActivity(Intent(this, RxJavaActivity::class.java))
            R.id.button_retrofit -> null
        }
    }
}