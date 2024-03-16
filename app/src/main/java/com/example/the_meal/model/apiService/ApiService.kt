package com.example.the_meal.model.apiService

import com.example.the_meal.model.dataClass.CategoriesResp
import retrofit2.http.GET

interface ApiService {

    @GET("categories.php")
    suspend fun getCategoriesList() : List<CategoriesResp>

}