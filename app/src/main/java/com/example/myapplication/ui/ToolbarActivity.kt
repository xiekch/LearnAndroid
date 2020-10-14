package com.example.myapplication.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_tool_bar.toolbar

class ToolbarActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_bar)

        setSupportActionBar(toolbar)
        toolbar?.setOnMenuItemClickListener(this)
        toolbar?.inflateMenu(R.menu.menu_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setNavigationOnClickListener { this.finish() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_notification, R.id.action_search, R.id.action_share -> Toast.makeText(
                this, item.title
                    ?: "item", Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }
}