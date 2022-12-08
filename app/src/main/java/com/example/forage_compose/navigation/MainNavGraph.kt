package com.example.forage_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.forage_compose.presentation.DetailsScreen
import com.example.forage_compose.presentation.InputScreen
import com.example.forage_compose.presentation.ListScreen
import com.example.forage_compose.utils.Constants

@Composable
fun MainNavGraph(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Constants.LIST_SCREEN
    )
    {
        composable(Constants.LIST_SCREEN){
            ListScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
            )
        }
        composable(
            route = Constants.DETAILS_SCREEN + "?forageId={forageId}",
            arguments = listOf(
                navArgument(name = "forageId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            DetailsScreen(
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(
            route = Constants.INPUT_SCREEN + "?forageId={forageId}",
            arguments = listOf(
                navArgument(name = "forageId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            InputScreen(
                onNavigate = {
                    navController.navigate(it.route)
                },
                navController = navController
            )
        }
    }
}