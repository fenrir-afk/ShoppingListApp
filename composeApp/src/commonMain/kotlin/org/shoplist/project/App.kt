package org.shoplist.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.shoplist.project.core.navigation.ShoppingListNavigation
import org.shoplist.project.shopList.presentation.ShoppingViewModel


@Composable
@Preview
fun App() {
    MaterialTheme {
        ShoppingListNavigation(
            modifier = Modifier.fillMaxSize()
        )
    }
}