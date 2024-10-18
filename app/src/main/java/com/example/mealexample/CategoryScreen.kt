package com.example.mealexample

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CategoryScreen(viewModel: MainViewModel){
    val categoryState = viewModel.categoryState.collectAsState()
    val chosenCategory = viewModel.chosenCategory.collectAsState()
    viewModel.getCategory(chosenCategory.value)
    Column(
        modifier = Modifier.fillMaxSize()
    ){

        if (categoryState.value.loading){
            CircularProgressIndicator()
        }
        if (categoryState.value.meals.isNotEmpty()){
            CategoryItems(categoryState.value.meals)
        }
        if (categoryState.value.error != null){
            Text(categoryState.value.error!!)
        }
    }
}

@Composable
fun CategoryItems(meals: List<Meal>) {
    LazyColumn {
        items(meals){
            MealItem(it)
        }

    }
}

@Composable
fun MealItem(meal: Meal) {
    Box(
        modifier = Modifier.height(200.dp).background(color = Color.DarkGray)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.height(150.dp),
                model = meal.strMealThumb,
                contentDescription = null
            )
            Spacer(Modifier.height(8.dp))
            Text(meal.strMeal)
        }
    }
}
