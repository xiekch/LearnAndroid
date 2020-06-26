package com.example.myapplication.intent

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_intent.*

class IntentActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        Log.d("IntentActivity", "create")
        logTaskName()
        jump_button1.setOnClickListener(this)
        jump_button2.setOnClickListener(this)
        jump_button3.setOnClickListener(this)
        jump_button4.setOnClickListener(this)
        share_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.jump_button1 -> {
                val intent1 = Intent()
                intent1.action = "android.intent.action.Jump"
                startActivityForResult(intent1, 0)
            }
            R.id.jump_button2 -> {
                val intent2 = Intent(this, IntentActivity::class.java)
                startActivity(intent2)
            }
            R.id.jump_button3 -> {
                val read1 = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.runoob.com"))
                startActivity(read1)
            }
            R.id.jump_button4 -> {
                val uri = Uri.parse("tel:10086")
                val call = Intent(Intent.ACTION_DIAL, uri)
                startActivity(call)
            }
            R.id.share_button -> {
                var share_intent = Intent()
                share_intent.action = Intent.ACTION_SEND // set action
                share_intent.type = "text/plain" // set the type to share
                share_intent.putExtra(Intent.EXTRA_SUBJECT, "share") //add subject
                share_intent.putExtra(Intent.EXTRA_TEXT, "share with you:" + "android") //add content
                share_intent = Intent.createChooser(share_intent, "share")
                startActivity(share_intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this@IntentActivity, data?.extras?.getString("title"), Toast.LENGTH_SHORT).show()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Toast.makeText(this@IntentActivity, "new Intent", Toast.LENGTH_SHORT).show()
        Log.i("intent", "new Intent")
        logTaskName()
    }

    private fun logTaskName() {
        Log.i("IntentActivity", "taskId:" + taskId + " hashcode:" + hashCode())
        try {
            val activityInfo = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
            Log.i("IntentActivity", activityInfo.taskAffinity)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }
}