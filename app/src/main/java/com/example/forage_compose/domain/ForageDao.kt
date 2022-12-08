package com.example.forage_compose.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ForageDao {

    @Query("SELECT * FROM Forage_Table ORDER BY id ASC ")
    fun getAll() : Flow<List<Forage>>

    @Query("SELECT * FROM FORAGE_TABLE WHERE id =:id ")
    suspend fun getForageById(id :Int) : Forage?

    @Query("UPDATE Forage_Table SET isSeason =:isSeason WHERE id =:id  ")
    suspend fun update(id: Int, isSeason : Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(forage: Forage)

    @Delete
    suspend fun deleteForage(forage: Forage)

    @Query("DELETE FROM Forage_Table")
    suspend fun deleteAll()


}