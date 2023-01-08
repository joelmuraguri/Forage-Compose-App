package com.example.forage_compose.domain

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ForageDao {

    @Query("SELECT * FROM Forage_Table ORDER BY id ASC ")
    fun getAll() : Flow<List<Forage>>

    @Query("SELECT * FROM FORAGE_TABLE WHERE id =:id ")
    suspend fun getForageById(id :Int) : Forage?

    @Update
    suspend fun update(forage: Forage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(forage: Forage)

    @Delete
    suspend fun deleteForage(forage: Forage)

    @Query("DELETE FROM Forage_Table")
    suspend fun deleteAll()

}