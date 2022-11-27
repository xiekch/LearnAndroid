package com.example.myapplication

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.animation.AnimationActivity
import com.example.myapplication.component.ComponentsIndexActivity
import com.example.myapplication.etc.EtcIndexActivity
import com.example.myapplication.fragment.ContainerActivity
import com.example.myapplication.intent.IntentActivity
import com.example.myapplication.library.LibraryIndexActivity
import com.example.myapplication.storage.StorageIndexActivity
import com.example.myapplication.thread.ThreadIndexActivity
import com.example.myapplication.ui.DisplayMessageActivity
import com.example.myapplication.ui.UIIndexActivity
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view1_1
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view1_2
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view1_3
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view2_1
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view2_2
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view2_3
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view3_1
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view3_2
import kotlinx.android.synthetic.main.constraint_layout_nine_squares.view3_3
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view1_1.setText(R.string.ui)
        view1_2.setText("Etc")
        view1_3.setText(R.string.animation)
        view2_1.setText(R.string.components)
        view2_2.setText("Intent")
        view2_3.setText("Fragment")
        view3_1.setText(R.string.thread)
        view3_2.setText(R.string.storage)
        view3_3.setText(R.string.library)

        view1_1.setOnClickListener(this)
        view1_2.setOnClickListener(this)
        view1_3.setOnClickListener(this)
        view2_1.setOnClickListener(this)
        view2_2.setOnClickListener(this)
        view2_3.setOnClickListener(this)
        view3_1.setOnClickListener(this)
        view3_2.setOnClickListener(this)
        view3_3.setOnClickListener(this)

        val am = getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        Log.i(TAG, "intent ${intent?.action} ${intent?.categories} ${am}")
    }

    /**
     * Called when the user taps the Send button
     */
    fun sendMessage(view: View?) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        val editText = findViewById<View>(R.id.editText) as EditText
        val message = editText.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }


    override fun onClick(v: View?) {
        val intent: Intent
        when (v?.id) {
            R.id.view1_1 -> {
                intent = Intent(this, UIIndexActivity::class.java)
                startActivity(intent)
            }
            R.id.view1_2 -> {
                intent = Intent(this, EtcIndexActivity::class.java)
                startActivity(intent)
            }
            R.id.view1_3 -> {
                intent = Intent(this, AnimationActivity::class.java)
                startActivity(intent)
            }
            R.id.view2_1 -> {
                intent = Intent(this, ComponentsIndexActivity::class.java)
                startActivity(intent)
            }
            R.id.view2_2 -> {
                intent = Intent()
                intent.setClass(this, IntentActivity::class.java)
                startActivity(intent)
            }
            R.id.view2_3 -> {
                intent = Intent(this, ContainerActivity::class.java)
                startActivity(intent)
            }
            R.id.view3_1 -> {
                intent = Intent(this, ThreadIndexActivity::class.java)
                startActivity(intent)
            }
            R.id.view3_2 -> {
                intent = Intent(this, StorageIndexActivity::class.java)
                startActivity(intent)
            }
            R.id.view3_3 -> {
                intent = Intent(this, LibraryIndexActivity::class.java)
                startActivity(intent)
            }
        }
    }
}