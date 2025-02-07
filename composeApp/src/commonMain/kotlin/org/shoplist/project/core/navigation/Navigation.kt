package org.shoplist.project.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import org.koin.compose.viewmodel.koinViewModel
import org.shoplist.project.core.presentation.ObserveAsEvents
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
    if(state.key != null){
        navController.navigate(Route.MainPage)
        viewModel.getAllLists(state.key!!)
        viewModel.getListsContent()
    }
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
                composable<Route.Registry> {
                    RegistryScreen(
                        modifier = modifier,
                        onCreateKey = {
                            viewModel.onAction(ShoppingActions.OnCreateKeyAction)
                        },
                        onCheckKey = { key:String ->
                            viewModel.onAction(ShoppingActions.OnCheckKeyAction(key))
                        }
                    )
                }
                composable<Route.MainPage>{
                    SliderScreen(
                        screenstate = state
                    )
                }
            }
        }
    }
}