package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class GestureActivity : AppCompatActivity() {
    private val mGestureDetector by lazy { GestureDetector(this, MGestureListener()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val TAG = "GestureActivity"
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }

    class MGestureListener : GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
        override fun onDown(e: MotionEvent?): Boolean {
            Log.i(TAG, "onDown")
            return true
        }

        override fun onShowPress(e: MotionEvent?) {
            Log.i(TAG, "onShowPress")
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            Log.i(TAG, "onSingleTapUp")
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            Log.i(TAG, "onScroll")
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            Log.i(TAG, "onLongPress")
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.i(TAG, "onFling")
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            Log.i(TAG, "onSingleTapConfirmed")
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            Log.i(TAG, "onDoubleTap")
            return true
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            Log.i(TAG, "onDoubleTapEvent")
            return true
        }
    }
}