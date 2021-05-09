package com.example.myapplication.component

import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R


class MyService : Service() {
    private val mBinder = MyBinder()
    private var times = 0

    companion object {
        const val TAG = "MyService"
    }

    override fun onBind(intent: Intent): IBinder {
        this.times++
        Log.i("MyService", "onBind $times")
        return mBinder
    }

    override fun onCreate() {
        Log.i("MyService", "onCreate")
        val CHANNEL_ONE_ID = "CHANNEL_ONE_ID"
        val CHANNEL_ONE_NAME = "CHANNEL_ONE_ID"
        var notificationChannel: NotificationChannel? = null
        // 进行8.0的判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.setShowBadge(true)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
        }

        val nfIntent = Intent(this, ServiceIndexActivity::class.java)
        val notification = NotificationCompat.Builder(this, "")
            .setChannelId(CHANNEL_ONE_ID)
            .setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0))
            .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
            .setContentTitle("Title")// 设置下拉列表里的标题
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentText("Service") // 设置上下文内容
            .setWhen(System.currentTimeMillis()) // 设置该通知发生的时间
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("MyService", "onStartCommand $startId ${this.times}")
        times++
        val thread = object : Thread() {
            override fun run() {
                sleep(5 * 1000)
                Log.d(TAG, "task done")
                stopSelf(startId)
            }
        }
        thread.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i("MyService", "destroy")
    }

    fun startDownload() {
        Log.i("MyService", "start download")
    }

    fun getProgress(): Int {
        Log.i("MyService", "get progress")
        return 0
    }

    inner class MyBinder : Binder() {
        fun getService(): MyService = this@MyService
    }
}
