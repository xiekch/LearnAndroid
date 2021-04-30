package com.example.myapplication.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.R
import androidx.appcompat.widget.AppCompatButton

class MyButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.buttonStyle
) : AppCompatButton(context, attrs, defStyleAttr) {

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        Toast.makeText(context, "onTouchEvent", Toast.LENGTH_SHORT).show()
        Log.d("MyButton", "onTouchEvent")
        return false
    }

}