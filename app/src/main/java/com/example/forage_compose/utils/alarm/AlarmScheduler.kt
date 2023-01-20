package com.example.forage_compose.utils.alarm

import com.example.forage_compose.domain.Forage

interface AlarmScheduler {
    fun  schedule(forage: Forage)
    fun  cancel(forage: Forage)
}