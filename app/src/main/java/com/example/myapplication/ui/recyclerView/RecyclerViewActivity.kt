package com.example.myapplication.ui.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_recycler_view.changeButton1
import kotlinx.android.synthetic.main.activity_recycler_view.changeButton2
import kotlinx.android.synthetic.main.activity_recycler_view.recyclerView

class RecyclerViewActivity : AppCompatActivity() {
    private val data = arrayListOf<String>()
    private var adapter: LinearLayoutRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
//        repeat(10) {
//            data.add(it.toString())
//        }

        recyclerView.adapter = LinearLayoutRecyclerViewAdapter(this, data).also { adapter = it }
        recyclerView.layoutManager = LinearLayoutManager(this)
        changeButton1.setOnClickListener {
            data.forEachIndexed { index, s -> data[index] = "-$s" }
            adapter?.notifyItemChanged(2)
        }

        changeButton2.setOnClickListener {
            repeat(5) {
                data.add("end")
            }
            adapter?.notifyItemRangeChanged(0, data.size)
//            adapter?.notifyItemRangeChanged(data.size - 5, data.size)
            adapter?.notifyItemRangeInserted(data.size - 5, data.size)
        }
    }


}