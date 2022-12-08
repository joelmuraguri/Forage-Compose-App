package com.example.forage_compose.domain

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Forage::class], version = 1, exportSchema = false)
abstract class ForageDatabase : RoomDatabase() {

    abstract val dao : ForageDao

}