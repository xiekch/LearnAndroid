package com.example.myapplication.etc

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.text.BidiFormatter
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.etc.musicplayer.MusicPlayerActivity
import kotlinx.android.synthetic.main.activity_etc_index.anrButton
import kotlinx.android.synthetic.main.activity_etc_index.buttonMusicPlayer
import kotlinx.android.synthetic.main.activity_etc_index.buttonOpenCamera
import kotlinx.android.synthetic.main.activity_etc_index.buttonRTLLanguage
import kotlinx.android.synthetic.main.activity_etc_index.buttonTest
import kotlinx.android.synthetic.main.activity_etc_index.oomButton
import kotlinx.android.synthetic.main.activity_etc_index.textMove
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EtcIndexActivity : AppCompatActivity(), View.OnClickListener, View.OnTouchListener {
    private val CAMERA = 1

    companion object {
        const val TAG = "EtcIndexActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        setContentView(R.layout.activity_etc_index)
        supportActionBar?.hide()

        buttonOpenCamera.setOnClickListener(this)
        buttonMusicPlayer.setOnClickListener(this)
        buttonRTLLanguage.setOnClickListener(this)
        textMove.setOnTouchListener(this)
        buttonTest.setOnClickListener(this)
        anrButton.setOnClickListener(this)
        oomButton.setOnClickListener(this)
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
                Intent(Intent.ACTION_PICK).setDataAndType(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*"
                ).apply {
                    startActivityForResult(
                        this,
                        CAMERA
                    )
                }
            }
            R.id.anrButton -> {
                try {
                    Thread.sleep(60 * 1000)
                } catch (e: Exception) {
                    Log.e(TAG, e.toString())
                    Toast.makeText(this, "end", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.oomButton -> {
                lifecycleScope.launch { testMemeory() }
            }
        }
    }

    private suspend fun testMemeory() {
        val cache = mutableListOf<IntArray>()
        for (i in 0..100) {
            Log.i(TAG, "$i size: ${i * 10}MB")
            delay(100)
            cache.add(IntArray((2.5 * 1024 * 1024).toInt()))
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