package com.example.myapplication.ui

import android.annotation.SuppressLint
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
import com.example.myapplication.ui.recyclerView.RecyclerIndexActivity
import kotlinx.android.synthetic.main.activity_layout_index.*

class UIIndexActivity : AppCompatActivity(), View.OnClickListener, OnTouchListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_index)

        initListener()
    }

    private fun initListener() {
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        buttonMyButton.setOnClickListener(this)
        buttonMyButton.setOnTouchListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button_collapsing_toolbar.setOnClickListener(this)
        button_list_view.setOnClickListener(this)
        button_recycler_view.setOnClickListener(this)
        buttonKeyboard.setOnClickListener(this)
        buttonFullScreen.setOnClickListener(this)
        buttonDrawerLayout.setOnClickListener(this)
        buttonGestureDetector.setOnClickListener(this)
        buttonNavigation.setOnClickListener(this)
        buttonViewPager2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
//        Toast.makeText(LayoutIndexActivity.this, "click", Toast.LENGTH_SHORT).show();
        var intent: Intent? = null
        when (v.id) {
            R.id.button1 -> {
                intent = Intent(this@UIIndexActivity, SysuActivity::class.java)
                startActivity(intent)
            }
            R.id.button2 -> {
                intent = Intent(this@UIIndexActivity, WebViewActivity::class.java)
                intent.putExtra(EXTRA_TYPE, 0)
                startActivity(intent)
            }
            R.id.button3 -> {
                intent = Intent(this@UIIndexActivity, WebViewActivity::class.java)
                intent.putExtra(EXTRA_TYPE, 1)
                startActivity(intent)
            }
            R.id.button4 -> {
                val toast = Toast(applicationContext)
                val view = LayoutInflater.from(this@UIIndexActivity)
                    .inflate(R.layout.customized_toast_layout, null)
                toast.view = view
                toast.show()
            }
            R.id.button5 -> {
                intent = Intent(this@UIIndexActivity, AlertDialogActivity::class.java)
                startActivity(intent)
            }
            R.id.button6 -> {
                intent = Intent(this@UIIndexActivity, ProgressBarActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonMyButton -> {
                Toast.makeText(this, "my button onClick", Toast.LENGTH_SHORT).show()
            }
            R.id.button8 -> {
                intent = Intent(this@UIIndexActivity, ActionBarActivity::class.java)
                startActivity(intent)
            }
            R.id.button9 -> {
                intent = Intent(this@UIIndexActivity, ToolbarActivity::class.java)
                startActivity(intent)
            }
            R.id.button_list_view -> {
                intent = Intent(this@UIIndexActivity, ListViewActivity::class.java)
                startActivity(intent)
            }
            R.id.button_recycler_view -> startActivity(
                Intent(
                    this,
                    RecyclerIndexActivity::class.java
                )
            )
            R.id.button_collapsing_toolbar -> {
                intent = Intent(this@UIIndexActivity, CollapsingToolbarActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonKeyboard -> startActivity(Intent(this, KeyboardActivity::class.java))
            R.id.buttonFullScreen -> startActivity(Intent(this, FullscreenActivity::class.java))
            R.id.buttonDrawerLayout -> startActivity(Intent(this, DrawerLayoutActivity::class.java))
            R.id.buttonGestureDetector -> startActivity(Intent(this, GestureActivity::class.java))
            R.id.buttonNavigation -> startActivity(Intent(this, NavigationActivity::class.java))
            R.id.buttonViewPager2 -> startActivity(Intent(this, ViewPagerActivity::class.java))
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (v.id) {
            R.id.buttonMyButton -> {
                // listener is prior to callback MyButton.onTouchEvent
                Toast.makeText(this@UIIndexActivity, "onTouch ${event.action}", Toast.LENGTH_SHORT)
                    .show()
                Log.i("MyButton Listener", "onTouch ${event.action}")
            }
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.i("activity", "onTouchEvent")
        return super.onTouchEvent(event)
    }

    companion object {
        private const val TAG = "UIIndexActivity"
        const val EXTRA_TYPE = "com.example.myapplication.layout.type"
    }
}