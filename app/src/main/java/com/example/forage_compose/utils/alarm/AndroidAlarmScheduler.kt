package com.example.forage_compose.utils.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.forage_compose.domain.Forage
import java.util.*

class AndroidAlarmScheduler(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(forage: Forage) {
        val calendar: Calendar = Calendar.getInstance().apply {
            forage.time = System.currentTimeMillis()

        }
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("WATER ME", forage.name)
        }

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            PendingIntent.getBroadcast(
                context,
                forage.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }


    override fun cancel(forage: Forage) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                forage.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}