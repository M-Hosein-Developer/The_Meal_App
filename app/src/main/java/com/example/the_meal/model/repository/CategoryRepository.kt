package com.example.the_meal.model.repository

import com.example.the_meal.model.dataClass.CategoriesResp
import kotlinx.coroutines.flow.Flow

interface CategoryRepository{

    suspend fun getDataByCategory() : Flow<List<CategoriesResp.Category>>

    suspend fun getDataByCategoryFromDb() : Flow<List<CategoriesResp.Category>>

}