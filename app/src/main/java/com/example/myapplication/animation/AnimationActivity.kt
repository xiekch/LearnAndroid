package com.example.myapplication.animation

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_animation.text_view_animation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimationActivity : AppCompatActivity(), View.OnClickListener {
    private var valueAnimator: ValueAnimator? = null
    private val exportProgress: MutableLiveData<Float> = MutableLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        val imageView = findViewById<ImageView>(R.id.imageView_animation_sysu)
        val animator = AnimatorInflater.loadAnimator(this, R.animator.fade_out)
        animator.setTarget(imageView)
        animator.start()
        val textView = findViewById<TextView>(R.id.text_view)
        textView.setOnClickListener(this)

        GlobalScope.launch(Dispatchers.IO) {
            showFakeProgress(20f, 100f, 10000L, exportProgress)
        }
        exportProgress.observe(this, Observer {
            text_view_animation.text = it.toString()
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.text_view -> {
                val elevationAnimation = ObjectAnimator.ofFloat(v, "elevation", 0f)
                elevationAnimation.repeatCount = 1
                elevationAnimation.duration = 800
                elevationAnimation.repeatMode = ValueAnimator.REVERSE
                elevationAnimation.start()
            }
        }
    }

    private fun showFakeProgress(
        from: Float,
        to: Float,
        duration: Long,
        exportProgress: MutableLiveData<Float>
    ) {
        if (from > to || duration <= 0) return
        runOnUiThread {
            valueAnimator?.cancel()
            valueAnimator = ValueAnimator.ofFloat(from, to).apply {
                this.duration = duration
                this.startDelay = 0
                this.addUpdateListener {
                    exportProgress.postValue(it.animatedValue as Float)
                }
                this.start()
            }
        }
    }
}