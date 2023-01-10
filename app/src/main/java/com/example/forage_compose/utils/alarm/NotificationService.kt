package com.example.forage_compose.utils.alarm

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.forage_compose.MainActivity
import com.example.forage_compose.R

class NotificationService(
    context: Context
) {

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun showNotification(context: Context, title : String, description : String){



    }

    companion object{
        const val ALARM_NOTIFICATION_ID = "message_id"
    }
}