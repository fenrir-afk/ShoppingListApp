package org.shoplist.project.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object FurnitureGraph: Route

    @Serializable
    data object MainPage: Route

    @Serializable
    data object Registry: Route
}