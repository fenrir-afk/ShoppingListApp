package org.shoplist.project.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.shoplist.project.shopList.presentation.RegistryScreen
import org.shoplist.project.shopList.presentation.SliderScreen


@Composable
fun ShoppingListNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(navController = navController, startDestination = Route.FurnitureGraph) {
            navigation<Route.FurnitureGraph>(
                startDestination = Route.Registry
            ) {
                composable<Route.Registry>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    RegistryScreen(
                        modifier = modifier,
                    ){
                        navController.navigate(Route.MainPage)
                    }
                }
                composable<Route.MainPage>(
                    enterTransition = {
                        slideInHorizontally { initialOffset ->
                            initialOffset
                        }
                    },
                    exitTransition = {
                        slideOutHorizontally { initialOffset ->
                            initialOffset
                        }
                    }
                ) {
                    SliderScreen()
                }
            }
        }
    }
}