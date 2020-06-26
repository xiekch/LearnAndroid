package com.example.myapplication.component

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

class MyService : Service() {
    private val mBinder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        Log.i("MyService", "onBind")
        return mBinder
    }

    override fun onCreate() {
        Log.i("MyService", "onCreate")
        val nfIntent = Intent(this, ServiceIndexActivity::class.java)
        val notification = NotificationCompat.Builder(this, "")
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
        Log.i("MyService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i("MyService", "destroy")
    }

    class MyBinder : Binder() {
        fun startDownload() {
            Log.i("MyService", "start download")
        }

        fun getProgress(): Int {
            Log.i("MyService", "get progress")
            return 0
        }
    }
}
