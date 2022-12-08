package com.example.forage_compose.di

import android.app.Application
import androidx.room.Room
import com.example.forage_compose.domain.ForageDatabase
import com.example.forage_compose.domain.repo.ForageRepo
import com.example.forage_compose.domain.repo.ForageRepoImplementation
import com.example.forage_compose.utils.Constants.FORAGE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : ForageDatabase{
        return Room.databaseBuilder(
            application,
            ForageDatabase::class.java,
            FORAGE_DATABASE
        ).build()


    }

    @Provides
    @Singleton
    fun provideRepository(database: ForageDatabase) : ForageRepo{
        return ForageRepoImplementation(database.dao)
    }
}
