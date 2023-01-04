package com.example.forage_compose.domain.repo

import com.example.forage_compose.domain.Forage
import com.example.forage_compose.domain.ForageDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ForageRepoImplementation(
    private val dao: ForageDao
) : ForageRepo {


    override fun getAll(): Flow<List<Forage>> {
        Dispatchers.IO
        return dao.getAll()
    }

    override suspend fun getForageById(id : Int): Forage? {
        return dao.getForageById(id)
    }

    override suspend fun update(forage: Forage) {
        return dao.update(forage)
    }


    override suspend fun insert(forage: Forage) {
        return dao.insert(forage)
    }

    override suspend fun deleteForage(forage: Forage) {
        return dao.deleteForage(forage)
    }

    override suspend fun deleteAll() {
        return dao.deleteAll()
    }

}