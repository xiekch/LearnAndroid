package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_change_translation.editText
import kotlinx.android.synthetic.main.activity_change_translation.surfaceView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChangeTranslationActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ChangeTranslationActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_translation)

        MainScope().launch {
            delay(1000L)
            surfaceView.translationY -= 100

            delay(1000L)
            val lp = editText.layoutParams as RelativeLayout.LayoutParams
            lp.bottomMargin = 100
            editText.layoutParams = lp

        }

        editText.doOnTextChanged { text, start, count, after ->
            Log.i(TAG, "$text $start $count $after")
        }
    }
}