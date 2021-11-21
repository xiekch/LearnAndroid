package com.example.myapplication.ui.recyclerView

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_layout_index.button2
import kotlinx.android.synthetic.main.activity_layout_index.button_recycler_view
import kotlinx.android.synthetic.main.activity_recycler_index.button1

class RecyclerIndexActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_index)

        setListener()
    }

    private fun setListener() {
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button_recycler_view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var intent: Intent? = null
        when (v?.id) {
            R.id.button1 -> {
                Toast.makeText(
                    this@RecyclerIndexActivity,
                    R.string.ListLayout,
                    Toast.LENGTH_SHORT
                ).show()
                intent = Intent(
                    this@RecyclerIndexActivity,
                    LinearLayoutRecyclerViewActivity::class.java
                )
                startActivity(intent)
            }
            R.id.button2 -> {
                Toast.makeText(
                    this@RecyclerIndexActivity,
                    R.string.StaggeredGrid,
                    Toast.LENGTH_SHORT
                ).show()
                intent = Intent(
                    this@RecyclerIndexActivity,
                    StaggeredGridRecyclerViewActivity::class.java
                )
                startActivity(intent)
            }
            R.id.button_recycler_view -> {
                intent = Intent(this@RecyclerIndexActivity, RecyclerViewActivity::class.java)
                startActivity(intent)
            }
        }
    }
}