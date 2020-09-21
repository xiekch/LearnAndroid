package com.example.myapplication.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.recyclerView.LinearLayoutRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_collapsing_toolbar.recycler_view

class CollapsingToolbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing_toolbar)
        supportActionBar?.hide()
        val data = mutableListOf("")
        repeat(100) {
            data.add("hello")
        }
        recycler_view.adapter = LinearLayoutRecyclerViewAdapter(this, data.toTypedArray())
    }
}