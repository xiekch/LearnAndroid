package com.example.myapplication.layout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_layout_index.*

class LayoutIndexActivity : AppCompatActivity(), View.OnClickListener, OnTouchListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_index)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnTouchListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button_list_view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
//        Toast.makeText(LayoutIndexActivity.this, "click", Toast.LENGTH_SHORT).show();
        var intent: Intent? = null
        when (v.id) {
            R.id.button1 -> {
                intent = Intent(this@LayoutIndexActivity, SysuActivity::class.java)
                startActivity(intent)
            }
            R.id.button2 -> {
                intent = Intent(this@LayoutIndexActivity, WebViewActivity::class.java)
                intent.putExtra(EXTRA_TYPE, 0)
                startActivity(intent)
            }
            R.id.button3 -> {
                intent = Intent(this@LayoutIndexActivity, WebViewActivity::class.java)
                intent.putExtra(EXTRA_TYPE, 1)
                startActivity(intent)
            }
            R.id.button4 -> {
                val toast = Toast(applicationContext)
                val view = LayoutInflater.from(this@LayoutIndexActivity).inflate(R.layout.customized_toast_layout, null)
                toast.view = view
                toast.show()
            }
            R.id.button5 -> {
                intent = Intent(this@LayoutIndexActivity, AlertDialogActivity::class.java)
                startActivity(intent)
            }
            R.id.button6 -> {
                intent = Intent(this@LayoutIndexActivity, ProgressBarActivity::class.java)
                startActivity(intent)
            }
            R.id.button8 -> {
                intent = Intent(this@LayoutIndexActivity, ActionBarActivity::class.java)
                startActivity(intent)
            }
            R.id.button9 -> {
                intent = Intent(this@LayoutIndexActivity, ToolBarActivity::class.java)
                startActivity(intent)
            }
            R.id.button_list_view -> {
                intent = Intent(this@LayoutIndexActivity, ListViewActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (v.id) {
            R.id.button7 -> {
                // listener is prior to callback MyButton.onTouchEvent
                Toast.makeText(this@LayoutIndexActivity, "Listener", Toast.LENGTH_SHORT).show()
                Log.i("MyButton Listener", "touched")
            }
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.i("activity", "touched")
        return super.onTouchEvent(event)
    }

    companion object {
        const val EXTRA_TYPE = "com.example.myapplication.layout.type"
    }
}