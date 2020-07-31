package com.example.myapplication.storage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_shared_preferences.*

class SharedPreferencesActivity : AppCompatActivity(), View.OnClickListener {
    private var editor: SharedPreferences.Editor? = null
    private var sharedPreferences: SharedPreferences? = null

    companion object {
        const val TAG = "SharedPreferences Activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        if (savedInstanceState != null) {
            Log.i(TAG, "savedInstance")
            edit_text.setText(savedInstanceState.getString("edit_text"))
            text_view_display.text = savedInstanceState.getString("text_view_display")
        }

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
        button_save.setOnClickListener(this)
        button_display.setOnClickListener(this)
        button_remove_all.setOnClickListener(this)
        edit_text.setOnClickListener(this)
        text_view_display.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_save -> {
                val text = edit_text.text.toString()
                if (text != "") {
                    editor?.putString(System.currentTimeMillis().toString(), text)
                    editor?.apply()
                    Toast.makeText(this@SharedPreferencesActivity, "saved", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.button_display -> {
                val all = sharedPreferences!!.all
                text_view_display.text = ""
                for ((key, value) in all) {
                    text_view_display.append("$key $value")
                }
            }
            R.id.button_remove_all -> {
                editor?.clear()?.commit()
                text_view_display.text = ""
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("edit_text", edit_text.text.toString())
        outState.putString("text_view_display", text_view_display.text.toString())
    }
}