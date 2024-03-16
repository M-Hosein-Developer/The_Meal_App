package com.example.the_meal.model.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CategoriesResp(
    val categories: List<Category>
) {
    @Entity
    data class Category(
        @PrimaryKey
        val idCategory: String,
        val strCategory: String,
        val strCategoryDescription: String,
        val strCategoryThumb: String
    )
}