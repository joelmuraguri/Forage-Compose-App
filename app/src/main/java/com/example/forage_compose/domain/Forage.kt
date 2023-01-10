package com.example.forage_compose.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.forage_compose.utils.Constants.ALARM_TABLE
import com.example.forage_compose.utils.Constants.FORAGE_TABLE
import kotlinx.parcelize.Parcelize
import java.sql.Time

@Parcelize
@Entity(tableName = FORAGE_TABLE)
data class Forage(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    @ColumnInfo(name = "Name")
    val name : String,
    @ColumnInfo(name = "Location")
    val location : String,
    @ColumnInfo(name = "Notes")
    val notes : String,
    @ColumnInfo(name = "isSeason")
    val isSeason : Boolean,
    @ColumnInfo(name = "reminder")
    val time : Long ?= null,
) : Parcelable

@Entity(tableName = ALARM_TABLE)
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    @ColumnInfo(name = "AlarmId")
    val alarmId : Int = Int.MIN_VALUE,
    @ColumnInfo(name = "time")
    val time: Long = 0L

)
