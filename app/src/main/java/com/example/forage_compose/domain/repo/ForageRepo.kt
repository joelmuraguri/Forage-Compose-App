package com.example.forage_compose.domain.repo

import com.example.forage_compose.domain.Forage
import kotlinx.coroutines.flow.Flow

interface ForageRepo {

    fun getAll() : Flow<List<Forage>>

    suspend fun getForageById(id:Int) : Forage?

    suspend fun update(isSeason : Boolean, id:Int)

    suspend fun insert(forage: Forage)

    suspend fun deleteForage(forage: Forage)

    suspend fun deleteAll()
}