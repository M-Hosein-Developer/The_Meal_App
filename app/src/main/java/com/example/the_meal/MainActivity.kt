package com.example.the_meal

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.the_meal.model.dataClass.CategoriesResp
import com.example.the_meal.ui.featue.DetailCategory
import com.example.the_meal.ui.featue.HomeScreen
import com.example.the_meal.ui.intent.CategoryIntent
import com.example.the_meal.ui.state.CategoryState
import com.example.the_meal.ui.theme.The_MealTheme
import com.example.the_meal.util.MyScreens
import com.example.the_meal.viewModel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val categoryViewModel : CategoryViewModel by viewModels()
    private lateinit var mealByCategory : MutableState<List<CategoriesResp.Category>>

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mealByCategory = mutableStateOf(listOf())

        lifecycleScope.launch {
            categoryViewModel.mealCategoryIntent.send(CategoryIntent.GetDataByCategory)
        }

        getDataByCategory()

        setContent {
            The_MealTheme {

                MainUi(mealByCategory.value)


            }
        }
    }

    private fun getDataByCategory(){
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

@Composable
fun MainUi(
    categoryData: List<CategoriesResp.Category>
) {

    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = MyScreens.HomeScreen.rout){

        composable(MyScreens.HomeScreen.rout){
            HomeScreen(categoryData , navController)
        }

        composable(
            route = MyScreens.DetailCategory.rout + "/{DetailCategory}",
            arguments = listOf(navArgument(MyScreens.DetailCategory.rout){type = NavType.StringType})
        ){
            DetailCategory(categoryData , it.arguments!!.getString(MyScreens.DetailCategory.rout , "") , navController)
        }

    }


}


