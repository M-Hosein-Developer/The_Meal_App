package com.example.the_meal.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.the_meal.model.repository.CategoryRepository
import com.example.the_meal.ui.intent.CategoryIntent
import com.example.the_meal.ui.state.CategoryState
import com.example.the_meal.util.NetworkChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository ,  private val applicationContext: Context) : ViewModel(){

    val mealCategoryIntent = Channel<CategoryIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<CategoryState>(CategoryState.Idle)
    val state : StateFlow<CategoryState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {

        viewModelScope.launch {

            mealCategoryIntent.consumeAsFlow().collect {
                when (it) {
                    is CategoryIntent.GetDataByCategory -> fetchCategoryMealData()
                }
            }

        }

    }

    private fun fetchCategoryMealData() {

        viewModelScope.launch {

            _state.value = CategoryState.Loading

            if (NetworkChecker(applicationContext).internetConnection){

                repository.getDataByCategory()
                    .catch { Log.v("errorCategory" , it.message.toString()) }
                    .collect{
                        _state.value = CategoryState.dataByCategory(it)
                    }
            }else{

                repository.getDataByCategoryFromDb()
                    .catch { Log.v("errorCategory" , it.message.toString()) }
                    .collect{
                        _state.value = CategoryState.dataByCategory(it)
                    }
            }


        }

    }

}