package com.example.myapplication.thread

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.Objects
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.android.synthetic.main.activity_thread_index.buttonCoroutine
import kotlinx.android.synthetic.main.activity_thread_index.button_cancel
import kotlinx.android.synthetic.main.activity_thread_index.button_download
import kotlinx.android.synthetic.main.activity_thread_index.button_post_delayed
import kotlinx.android.synthetic.main.activity_thread_index.button_send_message
import kotlinx.android.synthetic.main.activity_thread_index.button_tick
import kotlinx.android.synthetic.main.activity_thread_index.switch_load_data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class ThreadIndexActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "ThreadIndexActivity"
        const val MSG_SEND = 1
        const val MSG_TICK = 11
        const val MSG_TICK_STOP = 12
        const val MSG_DATA = 13
        const val MSG_DATA_STOP = 14
    }

    private val mainHandler: Handler = MainHandler(this)
    private val dataHandlerThread = HandlerThread("handler thread")
    private var checkDataHandler: Handler? = null
    private var downloadTask: DownloadTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_index)

        button_post_delayed.setOnClickListener(this)
        button_send_message.setOnClickListener(this)
        button_cancel.setOnClickListener(this)
        button_tick.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mainHandler.sendEmptyMessage(MSG_TICK)
            } else mainHandler.sendEmptyMessage(MSG_TICK_STOP)
        }
        button_download.setOnClickListener(this)
        switch_load_data.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (!dataHandlerThread.isAlive) {
                    dataHandlerThread.start()
                }
                if (checkDataHandler == null) checkDataHandler =
                    object : Handler(dataHandlerThread.looper) {
                        override fun handleMessage(msg: Message) {
                            when (msg.what) {
                                MSG_DATA -> {
                                    // update data
                                    mainHandler.sendMessage(
                                        Message.obtain(
                                            mainHandler,
                                            MSG_DATA,
                                            (Math.random() * 1000).toInt()
                                        )
                                    )
                                    checkDataHandler?.sendEmptyMessageDelayed(MSG_DATA, 3000)
                                }
                                MSG_DATA_STOP ->                                         // pause
                                    checkDataHandler?.removeCallbacksAndMessages(null)
                            }
                        }
                    }
                checkDataHandler?.sendEmptyMessage(MSG_DATA)
            } else {
                // pause
                checkDataHandler?.sendEmptyMessage(MSG_DATA_STOP)
            }
        }
        buttonCoroutine.setOnClickListener(this)
    }

    private fun printThread(id: Int) {
        Log.i(TAG, "$id: ${Thread.currentThread().name} ${Thread.currentThread().id}")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_post_delayed -> mainHandler.postDelayed({
                Toast.makeText(
                    this@ThreadIndexActivity,
                    "post delayed",
                    Toast.LENGTH_SHORT
                ).show()
            }, 2500)
            R.id.button_send_message -> object : Thread() {
                override fun run() {
                    mainHandler.sendMessage(Message.obtain(mainHandler, MSG_SEND))
                }
            }.start()
            R.id.button_cancel -> mainHandler.removeCallbacksAndMessages(null)
            R.id.button_download -> if (downloadTask == null) {
                downloadTask = DownloadTask(this)
                downloadTask?.execute()
            }
            R.id.buttonCoroutine -> {
                val ioScope = CoroutineScope(Dispatchers.IO)
                ioScope.launch {
                    printThread(1)
                }
                GlobalScope.launch {
                    printThread(2)
                    launch {
                        printThread(3)
                    }
                }
                GlobalScope.launch(Dispatchers.IO) {
                    printThread(4)
                }
                GlobalScope.launch(Dispatchers.Main) {
                    printThread(6)
                }
                val emptyScope = CoroutineScope(EmptyCoroutineContext)
                emptyScope.launch {
                    printThread(5)
                }
                runOnUiThread {
                    printThread(7)
                    emptyScope.launch {
                        printThread(9)
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacksAndMessages(null)
        checkDataHandler?.removeCallbacksAndMessages(null)
        downloadTask?.cancel(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dataHandlerThread.isAlive) dataHandlerThread.quit()
    }

    class DownloadTask(activity: Activity) : AsyncTask<String?, Int?, String>() {
        val activity = WeakReference<Activity>(activity)
        override fun onPreExecute() {
            super.onPreExecute()
            val pb = activity.get()?.findViewById<ProgressBar>(R.id.progressBar_download)
            pb?.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg strings: String?): String {
//            val len = getContentLength("https://download.virtualbox.org/virtualbox/6.1.10/virtualbox-6.1_6.1.10-138449~Ubuntu~bionic_amd64.deb")
            val len = getContentLength("https://www.baidu.com")
            Log.i("download length", "" + len)
            var i = 1
            while (i <= 100) {
                publishProgress(i)
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                    return "Failed"
                }
                i += 2
            }
            return "OK"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val pb = activity.get()?.findViewById<ProgressBar>(R.id.progressBar_download)
            pb?.progress = values[0]!!
        }

        override fun onPostExecute(s: String) {
            val context = activity.get() ?: return
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
        }

        override fun onCancelled(s: String) {
            val context = activity.get() ?: return
            Toast.makeText(context, "Download Failed", Toast.LENGTH_LONG).show()
        }

        private fun getContentLength(downloadUrl: String): Long {
            val client = OkHttpClient()
            val request = Request.Builder().url(downloadUrl).build()
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val contentLength = Objects.requireNonNull(response.body)?.contentLength() ?: 0
                    Objects.requireNonNull(response.body)?.close()
                    return contentLength
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return 0
        }

    }

    class MainHandler(context: ThreadIndexActivity) : Handler() {
        private var stop = false
        var count = 0
        private val contextReference: WeakReference<ThreadIndexActivity> = WeakReference(context)

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SEND -> Toast.makeText(
                    contextReference.get(),
                    msg.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                MSG_TICK -> if (!stop) {
                    count++
                    Toast.makeText(contextReference.get(), "tick $count", Toast.LENGTH_SHORT)
                        .show()
                    sendEmptyMessageDelayed(MSG_TICK, 3000)
                }
                MSG_TICK_STOP -> stop = true
                MSG_DATA -> contextReference.get()?.switch_load_data?.text = msg.obj.toString()
            }
            //            Toast.makeText(ThreadIndexActivity.this, "You will not see it", Toast.LENGTH_SHORT).show();
        }
    }
}