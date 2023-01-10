package com.example.forage_compose.di

import android.app.Application
import androidx.room.Room
import com.example.forage_compose.domain.ForageDatabase
import com.example.forage_compose.domain.auth.AuthRepo
import com.example.forage_compose.domain.auth.AuthRepoImplementation
import com.example.forage_compose.domain.repo.ForageRepo
import com.example.forage_compose.domain.repo.ForageRepoImplementation
import com.example.forage_compose.utils.Constants.FORAGE_DATABASE
import com.example.forage_compose.utils.alarm.AlarmRepo
import com.example.forage_compose.utils.alarm.AlarmRepoImpl
import com.google.firebase.auth.FirebaseAuth
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

    @Provides
    @Singleton
    fun providesAlarmRepo(db : ForageDatabase) : AlarmRepo{
        return AlarmRepoImpl(db.alarmDao)
    }

    @Provides
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepo(repo: AuthRepoImplementation) : AuthRepo = repo
}
