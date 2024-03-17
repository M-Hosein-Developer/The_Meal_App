package com.example.the_meal.model.repository

import com.example.the_meal.model.apiService.ApiService
import com.example.the_meal.model.dataClass.CategoriesResp
import com.example.the_meal.model.database.MyDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val apiService: ApiService , private val myDao: MyDao) : CategoryRepository {

    override suspend fun getDataByCategory(): Flow<List<CategoriesResp.Category>> = flow {
        while (true){
            val data = apiService.getCategoriesListOfMeal().categories
            emit(data)
            myDao.insertMealDataByCategory(data)
            delay(10000)
        }
    }

    override suspend fun getDataByCategoryFromDb(): Flow<List<CategoriesResp.Category>> = flow {
        while (true){
            emit(myDao.getMealDataByCategoryFromDb())
            delay(11000)
        }
    }

}