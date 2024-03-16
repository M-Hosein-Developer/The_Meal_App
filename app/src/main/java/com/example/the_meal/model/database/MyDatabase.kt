package com.example.the_meal.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.the_meal.model.dataClass.CategoriesResp

@Database(entities = [CategoriesResp::class] , version = 1 , exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao() : MyDao

}