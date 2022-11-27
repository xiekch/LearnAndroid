package com.example.myapplication.ui.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_black_theme.colorTextView
import kotlinx.android.synthetic.main.activity_white_theme.invalidateButton
import kotlinx.android.synthetic.main.activity_white_theme.textUnderCircle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WhiteThemeActivity : AppCompatActivity() {
    private val aLiveData = MutableLiveData<Boolean>(false)
    private val bLiveData = MutableLiveData<Boolean>(false)
    val TAG = "WhiteThemeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_white_theme)
        colorTextView.setTextColor(getColor(R.color.transparent_50_white))

        invalidateButton.setOnClickListener {
            textUnderCircle.invalidate()
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            aLiveData.observe(this@WhiteThemeActivity,
                Observer<Boolean> {
                    Toast.makeText(this@WhiteThemeActivity, "A", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "A")
                    Thread.dumpStack()
                })

            bLiveData.observe(this@WhiteThemeActivity,
                Observer<Boolean> {
                    Toast.makeText(this@WhiteThemeActivity, "B", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "B")
                    Thread.dumpStack()
                })
        }
    }
}

class TestLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val paint = Paint()

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.WHITE
        canvas?.drawCircle(400f, 400f, 200f, paint)
    }
}

class TestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    var invalidated = false
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        Toast.makeText(context, "invalidate", Toast.LENGTH_SHORT).show()
        canvas?.drawCircle(10f, 30f, 30f, paint)

//        if (!invalidated) {
////            invalidated = true
//            postInvalidateDelayed(5000)
//        }
    }
}