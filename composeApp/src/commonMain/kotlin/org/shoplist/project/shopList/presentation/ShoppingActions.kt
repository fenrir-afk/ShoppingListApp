package org.shoplist.project.shopList.presentation

sealed interface ShoppingActions {
    data object OnCreateKeyAction : ShoppingActions
    data class OnCheckKeyAction(val key:String) : ShoppingActions
}