package com.example.myapplication.etc

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.etc.musicplayer.MusicPlayerActivity
import kotlinx.android.synthetic.main.activity_etc_index.*

class EtcIndexActivity : AppCompatActivity(), View.OnClickListener {
    private val CAMERA = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etc_index)

        buttonOpenCamera.setOnClickListener(this)
        buttonMusicPlayer.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonOpenCamera -> Intent(ACTION_IMAGE_CAPTURE).apply {
                startActivityForResult(
                    this,
                    CAMERA
                )
            }
            R.id.buttonMusicPlayer -> startActivity(Intent(this, MusicPlayerActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.CAMERA && resultCode == RESULT_OK && data != null) {
            Toast.makeText(this, "a photo taken", Toast.LENGTH_SHORT).show()
        }
    }
}