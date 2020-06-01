package com.example.myapplication.layout

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class ActionBarActivity : AppCompatActivity(), View.OnClickListener {
    private var buttonHide: Button? = null
    private var buttonShow: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_bar)
        this.supportActionBar?.title = "Title"
        supportActionBar?.subtitle = "SubTitle"

        buttonHide = findViewById(R.id.button_hide)
        buttonShow = findViewById(R.id.button_show)
        buttonHide?.setOnClickListener(this)
        buttonShow?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_hide -> supportActionBar?.hide()
            R.id.button_show -> supportActionBar?.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.title ?: "item", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onNavigateUp(): Boolean {
        finish()
        //super.onNavigateUp()
        return true
    }
}