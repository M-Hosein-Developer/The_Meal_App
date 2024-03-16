package com.example.the_meal.model.apiService

import com.example.the_meal.model.dataClass.CategoriesResp
import retrofit2.http.GET

interface ApiService {

    @GET("api/json/v1/1/categories.php")
    suspend fun getCategoriesListOfMeal() : CategoriesResp

}