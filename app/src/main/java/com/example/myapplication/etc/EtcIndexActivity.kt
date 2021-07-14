package com.example.myapplication.etc

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.text.BidiFormatter
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.etc.musicplayer.MusicPlayerActivity
import kotlinx.android.synthetic.main.activity_etc_index.*
import kotlinx.android.synthetic.main.activity_thread_index.*

class EtcIndexActivity : AppCompatActivity(), View.OnClickListener, View.OnTouchListener {
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
        textMove.setOnTouchListener(this)
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
//                textMove.layout(-50, 0, -50 + textMove.width, textMove.height)
//                textMove.translationX
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


    var preX = 0
    var preY = 0
    override fun onTouch(v: View, event: MotionEvent?): Boolean {
        if (event == null) return false
        Log.i(TAG, "action ${event.action}")
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
//        Log.i(TAG, "scroll ${v.scrollX} ${v.scrollY}")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                preX = x
                preY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x - preX
                val deltaY = y - preY
                Log.i(TAG, "$x $y -- $deltaX $deltaY -- $preX $preY")
                v.layout(v.left + deltaX, v.top + deltaY, v.right + deltaX, v.bottom + deltaY)
//                (v.parent as View).scrollBy(-deltaX,-deltaY)
                preX = x
                preY = y
                return true
            }
            MotionEvent.ACTION_UP -> {
//                Log.i(TAG, "scroll ${(v.parent as View).scrollX} ${(v.parent as View).scrollY}")
            }
        }
        return true
    }
}