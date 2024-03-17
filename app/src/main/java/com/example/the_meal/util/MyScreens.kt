package com.example.the_meal.util

sealed class MyScreens(val rout : String) {

    object HomeScreen : MyScreens("HomeScreen")
    object DetailCategory : MyScreens("DetailCategory")

}