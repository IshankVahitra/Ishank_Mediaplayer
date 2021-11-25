package com.example.ishank_mediaplayer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import java.util.*

class MediaPlayerService : Service() {
    private val mBinder: IBinder = MyBinder()

    val CHANNEL_ID = "Random number notification"

    private val mGenerator: Random = Random()
    private var counter = 1

    // LiveData for capturing random number generated by the service
    val randomNumberLiveData: MutableLiveData<Int> = MutableLiveData()

    override fun onBind(p0: Intent?): IBinder? {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        startNotification()

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                val randomNumber = mGenerator.nextInt(100)
                randomNumberLiveData.postValue(counter++)
                mainHandler.postDelayed(this, 1000)
            }
        })
    }

    inner class MyBinder : Binder() {
        val service: MediaPlayerService
            get() = this@MediaPlayerService
    }

    private fun startNotification() {
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("A service is running in the background")
            .setContentText("Generating random number").build()
        startForeground(1, notification)
    }
}