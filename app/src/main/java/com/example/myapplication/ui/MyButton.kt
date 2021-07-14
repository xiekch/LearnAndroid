package com.example.myapplication.ui

import android.content.Context
import android.graphics.*
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
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        style = Paint.Style.FILL
    }

    private val rect = Rect()
    private val bitmap =
        BitmapFactory.decodeResource(resources, com.example.myapplication.R.mipmap.flower)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Toast.makeText(context, "onTouchEvent", Toast.LENGTH_SHORT).show()
        Log.d("MyButton", "onTouchEvent")
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        super.onDraw(canvas)
        canvas.drawCircle(10f, 10f, 30f, paint)
        canvas.drawCircle(width - 10f, height - 10f, 30f, paint)
        rect.set(0, 0, 50, 50)
        canvas.drawBitmap(bitmap, null, rect, null)
    }
}