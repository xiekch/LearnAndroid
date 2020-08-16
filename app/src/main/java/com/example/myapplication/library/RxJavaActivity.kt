package com.example.myapplication.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.myapplication.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java.button_rxJava_start
import kotlinx.android.synthetic.main.activity_rx_java.textView_rxJava
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class RxJavaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        button_rxJava_start.setOnClickListener {
            rxTest()
            it.isEnabled = false
        }
    }


    fun rxTest() {
        Flowable.create<Int>(
            { emitter ->
                for (i in 1..50) {
                    emitter.onNext(i)
                    Thread.sleep(100)
                }
                emitter.onComplete()
            }, BackpressureStrategy.BUFFER
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Int> {
                override fun onComplete() {
                    Log.i(TAG, "complete")
                    button_rxJava_start.isEnabled = true
                }

                override fun onSubscribe(s: Subscription?) {
                    Log.i(TAG, "onSubscribe")
                    s?.request(100)
                }

                override fun onNext(t: Int?) {
                    textView_rxJava.text = t.toString()
                }

                override fun onError(t: Throwable?) {
                    Log.i(TAG, "onError")
                    t?.printStackTrace()
                }
            })
    }

    companion object {
        const val TAG = "RxJavaActivity"
    }
}