package com.example.forage_compose.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.forage_compose.utils.Constants.FORAGE_TABLE

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

)
