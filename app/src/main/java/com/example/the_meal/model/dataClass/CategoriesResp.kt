package com.example.the_meal.model.dataClass

data class CategoriesResp(
    val categories: List<Category>
) {
    data class Category(
        val idCategory: String,
        val strCategory: String,
        val strCategoryDescription: String,
        val strCategoryThumb: String
    )
}