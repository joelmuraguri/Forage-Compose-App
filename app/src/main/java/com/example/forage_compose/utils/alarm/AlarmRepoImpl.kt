package com.example.forage_compose.utils.alarm

import com.example.forage_compose.domain.Alarm
import com.example.forage_compose.domain.AlarmDao
import javax.inject.Inject

class AlarmRepoImpl @Inject constructor(
    private val dao: AlarmDao
) : AlarmRepo {
    override fun insert(alarm: Alarm) {
        return dao.insert(alarm)
    }

    override fun deleteOne(alarm: Alarm) {
        return dao.deleteAlarm(alarm)
    }
}