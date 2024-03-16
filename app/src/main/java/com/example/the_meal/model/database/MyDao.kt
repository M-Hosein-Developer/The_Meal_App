package com.example.the_meal.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.the_meal.model.dataClass.CategoriesResp

@Dao
interface MyDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealDataByCategory(data : List<CategoriesResp.Category>)

}