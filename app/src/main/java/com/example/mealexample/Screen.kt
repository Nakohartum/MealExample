package com.example.mealexample

object ScreenRoute {
    val mainScreen = Screen(route = "MainScreen")
    val categoryScreen = Screen(route = "CategoryScreen")
}

data class Screen(
    val route: String
)