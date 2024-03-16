package com.example.the_meal.ui.state

import com.example.the_meal.model.dataClass.CategoriesResp

sealed class CategoryState {

    object Idle : CategoryState()
    object Loading : CategoryState()
    data class dataByCategory(val data : List<CategoriesResp.Category>) : CategoryState()
    data class Error(val error : String?) : CategoryState()

}