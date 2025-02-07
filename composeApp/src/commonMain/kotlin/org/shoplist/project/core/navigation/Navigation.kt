package org.shoplist.project.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.compose.viewmodel.koinViewModel
import org.shoplist.project.core.presentation.ObserveAsEvents
import org.shoplist.project.core.presentation.toUiText
import org.shoplist.project.shopList.presentation.OneTimeEvent
import org.shoplist.project.shopList.presentation.RegistryScreen
import org.shoplist.project.shopList.presentation.ShoppingActions
import org.shoplist.project.shopList.presentation.ShoppingViewModel
import org.shoplist.project.shopList.presentation.SliderScreen


@Composable
fun ShoppingListNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val viewModel = koinViewModel<ShoppingViewModel>()
    val toaster = rememberToasterState()
    val state by viewModel.state.collectAsStateWithLifecycle()
    Toaster(state = toaster)
    ObserveAsEvents(events = viewModel.event) {event ->
        when(event){
            is OneTimeEvent.Error -> {
                toaster.show(event.error)
            }
        }
    }

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
                        navController = navController, // Другого выхода я увы пока не нашёл
                        state = state,
                        onCreateKey = {
                            viewModel.onAction(ShoppingActions.OnCreateKeyAction)
                        },
                        onCheckKey = { key:String ->
                            viewModel.onAction(ShoppingActions.OnCheckKeyAction(key))

                        }
                    )
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