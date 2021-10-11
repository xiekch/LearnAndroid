package com.example.myapplication.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_accept_share.sharedImage
import kotlinx.android.synthetic.main.activity_accept_share.sharedText

class AcceptShareActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AcceptShareActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accept_share)
        Log.i(TAG, "$intent ${intent.action} ${intent.data} ${intent.extras}")
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let { sharedText.text = it }
        val uri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
        Log.i(TAG, "$uri")
        uri.toString().takeIf { it.isNotEmpty() }.let { sharedImage.setImageURI(uri) }
    }
}