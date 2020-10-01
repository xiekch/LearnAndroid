package com.example.myapplication.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    data class Fruit(val name: String, val imageId: Int)

    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        initFruits()
        list_view.adapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        list_view.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, fruitList[position].name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
        repeat(20) {
            fruitList.add(Fruit("Apple", R.drawable.apple_pic))
            fruitList.add(Fruit("Banana", R.drawable.banana_pic))
            fruitList.add(Fruit("Orange", R.drawable.orange_pic))
        }
    }

    class FruitAdapter(activity: Activity, val resourceId: Int, data: List<Fruit>) :
            ArrayAdapter<Fruit>(activity, resourceId, data) {
        inner class ViewHolder(val fruitImage: ImageView, val fruitName: TextView)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View
            val viewHolder: ViewHolder
            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(resourceId, parent, false)
                val fruitImage = view.findViewById<ImageView>(R.id.fruitImage)
                val fruitName = view.findViewById<TextView>(R.id.fruitName)
                viewHolder = ViewHolder(fruitImage, fruitName)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }
            getItem(position)?.apply {
                viewHolder.fruitImage.setImageResource(imageId)
                viewHolder.fruitName.text = name
            }
            return view
        }
    }
}