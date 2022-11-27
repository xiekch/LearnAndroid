package com.example.myapplication.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CanvasActivity : AppCompatActivity() {
    companion object {
        const val TAG = "CanvasActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)

        val matrix = Matrix()
        matrix.setRotate(10f)
        Log.i(TAG, matrix.toString())

        matrix.setRotate(10f)
        Log.i(TAG, matrix.toString())
    }

}

class CavansView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val mPaint = Paint()
    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        //先通过canvas.translate操作Canvas的绘图坐标系，操作操作后的绘图坐标系原点处于操作前（100，100)的位置
        canvas.translate(100f, 100f)
        mPaint.setColor(Color.BLUE)
        //以坐标（100，100)的位置为原点，绘制一个半径100的圆，因为坐标系已经平移过了，所以这个原点其实是没平移前的（200，200)
        canvas.drawCircle(100f, 100f, 100f, mPaint)
        canvas.save()
        mPaint.setColor(Color.YELLOW)
        canvas.drawCircle(200f, 200f, 100f, mPaint)

        //再次平移
        canvas.translate(400f, 0f)
        mPaint.setColor(Color.BLUE)
        canvas.drawCircle(100f, 100f, 100f, mPaint)


        //这里使用了saveLayer，并且传入的参数0x7f其实是50%的透明度，也就是对接下来要入栈了一个图层，并且之后在这个图层上画的图像，进行了半透明的处理
        canvas.saveLayerAlpha(0f, 0f, 300f, 300f, 0x7f)
        mPaint.setColor(Color.YELLOW)
        canvas.drawCircle(200f, 200f, 100f, mPaint)
        canvas.restore()
        canvas.restore()

        mPaint.setShadowLayer(2F, 2F, 0F, 0xff0000)
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 2f
        canvas.drawRect(Rect(200, 200, 300, 400), mPaint)

    }
}