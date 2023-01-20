package com.example.forage_compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ForageApp : Application(){
//
//    override fun onCreate() {
//        super.onCreate()
//        createNotification()
//    }
//
//    private fun createNotification(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                NotificationService.ALARM_NOTIFICATION_ID,
//                "Reminder",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            channel.description = "Used for forage reminder"
//
//            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
}