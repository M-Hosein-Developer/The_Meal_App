package com.example.the_meal.di.module

import android.content.Context
import androidx.room.Room
import com.example.the_meal.model.database.MyDao
import com.example.the_meal.model.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun roomDatabase(@ApplicationContext context: Context) : MyDatabase = Room.databaseBuilder(context , MyDatabase::class.java , "roomDb.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(database : MyDatabase) : MyDao = database.myDao()

}