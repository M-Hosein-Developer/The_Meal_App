package com.example.the_meal.di.module

import com.example.the_meal.model.repository.CategoryRepository
import com.example.the_meal.model.repository.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun categoryRepository(repository : CategoryRepositoryImpl) : CategoryRepository

}