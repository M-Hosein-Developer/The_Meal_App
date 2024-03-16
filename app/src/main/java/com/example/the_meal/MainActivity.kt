package com.example.the_meal

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.lifecycleScope
import com.example.the_meal.model.dataClass.CategoriesResp
import com.example.the_meal.ui.intent.CategoryIntent
import com.example.the_meal.ui.state.CategoryState
import com.example.the_meal.ui.theme.The_MealTheme
import com.example.the_meal.viewModel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val categoryViewModel : CategoryViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mealByCategory = mutableStateOf(listOf<CategoriesResp.Category>())


        setContent {
            The_MealTheme {

                lifecycleScope.launch {
                    categoryViewModel.mealCategoryIntent.send(CategoryIntent.GetDataByCategory)
                }

                lifecycleScope.launch {

                    categoryViewModel.state.collect{

                        when(it){

                            is CategoryState.Idle -> {
                                Toast.makeText(this@MainActivity, "Idle...", Toast.LENGTH_SHORT).show()
                            }

                            is CategoryState.Loading -> {
                                Toast.makeText(this@MainActivity, "loading...", Toast.LENGTH_LONG).show()
                            }

                            is CategoryState.dataByCategory -> {
                                mealByCategory.value = it.data
                                Log.v("testData1" , it.data.toString())
                            }

                            is CategoryState.Error -> {
                                Log.v("CategoryError" , it.toString())
                            }

                        }

                    }

                }

            }
        }
    }
}

