package com.example.myapplication.intent

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        Log.i("NextActivity", "create")
        logTaskName()
        go_back_button.setOnClickListener {
            getResult()
        }
    }

    private fun logTaskName() {
        Log.d("NextActivity", "taskId:" + taskId + " hashcode:" + hashCode())
        try {
            val activityInfo = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
            Log.i("NextActivity", activityInfo.taskAffinity)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun getResult() {
        val intent = Intent()
        val bundle = Bundle()
        bundle.putString("title", "I am back")
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("title", "I am back")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}