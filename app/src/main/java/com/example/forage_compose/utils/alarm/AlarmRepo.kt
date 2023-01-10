package com.example.forage_compose.utils.alarm

import com.example.forage_compose.domain.Alarm

interface AlarmRepo {

    fun insert(alarm: Alarm)
    fun deleteOne(alarm: Alarm)
}