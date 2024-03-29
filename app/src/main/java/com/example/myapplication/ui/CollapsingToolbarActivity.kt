package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.ui.recyclerView.LinearLayoutRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_collapsing_toolbar.recycler_view

class CollapsingToolbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing_toolbar)
        val data = mutableListOf("")
        repeat(5) {
            data.add("hello")
        }
        recycler_view.adapter = LinearLayoutRecyclerViewAdapter(this, data)
    }
}