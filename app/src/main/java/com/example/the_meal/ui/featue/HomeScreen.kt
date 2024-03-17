package com.example.the_meal.ui.featue

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.the_meal.model.dataClass.CategoriesResp

@Composable
fun HomeScreen(categoryData: List<CategoriesResp.Category>) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        HomeToolbar()

        MealCategory(categoryData){

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolbar() {

    TopAppBar(
        title = {
            Text(
                text = "Home",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.White)
    )

}

@Composable
fun MealCategory(categoryData: List<CategoriesResp.Category> , onItemClicked :(String) -> Unit) {

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categoryData.size){
            MealCategoryItem(category = categoryData[it]){ onItemClicked.invoke(it) }
        }
    }

}

@Composable
fun MealCategoryItem(category: CategoriesResp.Category , onItemClicked :(String) -> Unit) {

    Card(
        modifier = Modifier
            .padding(12.dp)
            .clickable { onItemClicked.invoke(category.idCategory) },
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(6.dp),
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){


            AsyncImage(
                model = category.strCategoryThumb,
                contentDescription = null,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = category.strCategory,
                Modifier.padding(top = 8.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = category.strCategoryDescription,
                Modifier.padding(top = 8.dp , bottom = 4.dp),
                maxLines = 2,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )

        }

    }

}

