package com.example.the_meal.ui.featue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.the_meal.model.dataClass.CategoriesResp
import com.example.the_meal.util.EMPTY_DATA

@Composable
fun DetailCategory(
    categoryData: List<CategoriesResp.Category>,
    category: String,
    navController: NavHostController
){

    val data = remember { mutableStateOf(EMPTY_DATA) }

    categoryData.forEach {
        if (it.idCategory == category) data.value = it
    }



    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {

        CategoryDetailToolbar(data){
            navController.popBackStack()
        }
        MoreDetail(data)

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailToolbar(data: MutableState<CategoriesResp.Category> ,  onBackCLicked: () -> Unit) {

    TopAppBar(
        title = {
            Text(
                text = data.value.strCategory,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackCLicked.invoke() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.White)
    )

}

@Composable
fun MoreDetail(data: MutableState<CategoriesResp.Category>) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = data.value.strCategoryThumb,
            contentDescription =null,
            modifier = Modifier.size(300.dp)
            )

        Text(
            text = data.value.strCategory,
            Modifier.padding(top = 18.dp),
            style = TextStyle(
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = data.value.strCategoryDescription,
            Modifier.padding(18.dp).padding(top = 24.dp),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
        )
    }

}