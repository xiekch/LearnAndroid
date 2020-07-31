package com.example.myapplication.library

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class OkHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)
        buttonHttp.setOnClickListener { sendRequestWithOkHttp() }
    }

    fun sendRequestWithOkHttp() {
        thread {
            try {
                val url = "https://www.csdn.net"
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    showResponse(responseBody)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showResponse(response: String) {
        runOnUiThread {
            textHttp.text = response
        }
    }

    companion object {
        const val TAG = "OkHttpActivity"
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, OkHttpActivity::class.java))
        }
    }
}