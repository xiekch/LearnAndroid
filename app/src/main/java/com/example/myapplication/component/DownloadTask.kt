package com.example.myapplication.component

import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class DownloadTask : AsyncTask<String, Int, Int>() {
    override fun doInBackground(vararg params: String?): Int {
        var okHttpClient = OkHttpClient()
        return 0
    }

    private fun getContentLength(downloadUrl: String): Long {
        val client = OkHttpClient()
        val request: Request = Request.Builder().url(downloadUrl).build()
        try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val contentLength: Long = response.body?.contentLength() ?: 0
                response.body?.close()
                return contentLength
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }
}