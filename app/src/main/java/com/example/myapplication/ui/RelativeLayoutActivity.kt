package com.example.myapplication.ui

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_relative_layout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RelativeLayoutActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "RelativeLayoutActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_layout)
        buttonMoveLeft.setOnClickListener(this)
        buttonTranslationX.setOnClickListener(this)
        buttonMarginLeft.setOnClickListener(this)
        buttonAdjust.setOnClickListener(this)
        buttonAnimation.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.buttonMoveLeft -> {
                relativeView1.setPaddingRelative(relativeView1.paddingStart - 10, 0, 0, 0)
            }
            R.id.buttonTranslationX -> {
                relativeView3.translationX = 10f
                Log.i(
                    TAG,
                    "translationX ${relativeView3.translationX} x ${relativeView3.x} left ${relativeView3.left}"
                )
            }
            R.id.buttonMarginLeft -> {
                val layoutParams = relativeView1.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.marginStart += 10
                relativeView1.layoutParams = layoutParams
            }
            R.id.buttonAdjust -> {
                Log.i(
                    TAG,
                    "left ${linearLayoutAdjust.left} right ${linearLayoutAdjust.right} width ${linearLayoutAdjust.width} " +
                        "view left ${viewInLinearLayoutAdjust.left} view right ${viewInLinearLayoutAdjust.right} view width ${viewInLinearLayoutAdjust.width}"
                )
                val layoutParams = linearLayoutAdjust.layoutParams as RelativeLayout.LayoutParams
                layoutParams.width = 100
                linearLayoutAdjust.layoutParams = layoutParams
                Log.i(
                    TAG,
                    "left ${linearLayoutAdjust.left} right ${linearLayoutAdjust.right} width ${linearLayoutAdjust.width} " +
                        "view left ${viewInLinearLayoutAdjust.left} view right ${viewInLinearLayoutAdjust.right} view width ${viewInLinearLayoutAdjust.width}"
                )
                GlobalScope.launch {
                    delay(1000)
                    runOnUiThread {
                        Log.i(
                            TAG,
                            "left ${linearLayoutAdjust.left} right ${linearLayoutAdjust.right} width ${linearLayoutAdjust.width} " +
                                "view left ${viewInLinearLayoutAdjust.left} view right ${viewInLinearLayoutAdjust.right} view width ${viewInLinearLayoutAdjust.width}"
                        )
                    }
                }
            }
            R.id.buttonAnimation -> {
                val pressScaleAnim: ValueAnimator =
                    ValueAnimator.ofFloat(0F, 1F).apply { duration = 1000 }
                val width = relativeView1.width - 50
                val height = relativeView1.height
                pressScaleAnim.addUpdateListener {
                    relativeView1.layoutParams = RelativeLayout.LayoutParams(
                        (50 + (1 - it.animatedValue as Float) * width).toInt(),
                        height
                    )
                    relativeView2.layoutParams = RelativeLayout.LayoutParams(
                        (50 + (1 - it.animatedValue as Float) * width).toInt(),
                        height
                    )
                    relativeView3.layoutParams = RelativeLayout.LayoutParams(
                        (50 + (1 - it.animatedValue as Float) * width).toInt(),
                        height
                    )
                    Log.i(
                        TAG,
                        "width ${relativeView1.width} ${relativeView2.width} ${relativeView3.width}"
                    )
                    Log.i(
                        TAG,
                        "right ${relativeView1.right} ${relativeView2.right} ${relativeView3.right}"
                    )
                }
                pressScaleAnim.start()
            }

        }
    }
}