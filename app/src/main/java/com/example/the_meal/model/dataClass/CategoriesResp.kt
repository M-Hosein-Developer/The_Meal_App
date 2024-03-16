package com.example.the_meal.model.dataClass

import androidx.room.Entity

data class CategoriesResp(
    val categories: List<Category>
) {
    @Entity
    data class Category(
        val idCategory: String,
        val strCategory: String,
        val strCategoryDescription: String,
        val strCategoryThumb: String
    )
}