package com.example.forage_compose.domain.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.forage_compose.domain.Forage
import com.example.forage_compose.domain.ForageDao
import com.example.forage_compose.domain.ForageDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@SmallTest
class ForageDaoTest {

    private lateinit var database: ForageDatabase
    private lateinit var dao: ForageDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ForageDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.dao

    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertForage() = runBlocking{

        val forage1 = Forage(id = 1, name = "Apple", location = "Home", notes = "Yummy", isSeason = false)
        dao.insert(forage1)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dao.getAll().collect { forages ->
                assert(forages.contains(forage1))
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()


    }

    @Test
    fun updateForage() = runBlocking{
        val forage1 = Forage(id = 1, name = "Apple", location = "Home", notes = "Yummy", isSeason = false)
        dao.insert(forage1)

        val updatedForage = Forage(id = 1, name = "Apple", location = "Poland", notes = "mmm", isSeason = true)
        dao.update(updatedForage)

        val result = updatedForage.id?.let { dao.getForageById(it) }

        assert(result?.name == updatedForage.name)

    }

    @Test
    fun deleteForage() = runBlocking{

        val forage1 = Forage(id = 1, name = "Apple", location = "Home", notes = "Yummy", isSeason = false)
        val forage2 = Forage(id = 2, name = "Mango", location = "Somewhere", notes = "Munch", isSeason = true)
        dao.insert(forage1)
        dao.insert(forage2)

        dao.deleteForage(forage1)


        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dao.getAll().collect { forages ->
                assert(forages.isNotEmpty())
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()




    }

    @Test
    fun deleteAllForage() = runBlocking{

        val forage1 = Forage(id = 1, name = "Apple", location = "Home", notes = "Yummy", isSeason = false)
        val forage2 = Forage(id = 2, name = "Mango", location = "Somewhere", notes = "Munch", isSeason = true)
        dao.insert(forage1)
        dao.insert(forage2)

        dao.deleteAll()

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dao.getAll().collect { forages ->
                assert(forages.isEmpty())
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }


}