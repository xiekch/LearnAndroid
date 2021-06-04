package com.example.myapplication.etc

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.text.BidiFormatter
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.etc.musicplayer.MusicPlayerActivity
import kotlinx.android.synthetic.main.activity_etc_index.*

class EtcIndexActivity : AppCompatActivity(), View.OnClickListener {
    private val CAMERA = 1

    companion object {
        const val TAG = "EtcIndexActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etc_index)

        buttonOpenCamera.setOnClickListener(this)
        buttonMusicPlayer.setOnClickListener(this)
        buttonRTLLanguage.setOnClickListener(this)
        buttonTest.setOnClickListener(this)
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
            R.id.buttonRTLLanguage -> rTLLanguage()
            R.id.buttonTest -> {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.CAMERA && resultCode == RESULT_OK && data != null) {
            Toast.makeText(this, "a photo taken", Toast.LENGTH_SHORT).show()
        }
    }

    fun rTLLanguage() {
        val mySuggestion = "15 Bay Street, Laurel, CA"
        val myBidiFormatter: BidiFormatter = BidiFormatter.getInstance()

        // The "did_you_mean" localized string resource includes
        // a "%s" placeholder for the suggestion.
        Toast.makeText(
            this,
            String.format(getString(R.string.did_you_mean), mySuggestion),
            Toast.LENGTH_SHORT
        ).show()
        Toast.makeText(
            this,
            String.format(
                getString(R.string.did_you_mean),
                myBidiFormatter.unicodeWrap(mySuggestion)
            ),
            Toast.LENGTH_SHORT
        ).show()
    }
}