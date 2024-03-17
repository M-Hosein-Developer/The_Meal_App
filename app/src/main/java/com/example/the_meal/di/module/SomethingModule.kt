package com.example.the_meal.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SomethingModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context : Context) : Context = context

}