package org.shoplist.project.shopList.presentation

import org.shoplist.project.core.presentation.UiText
import org.shoplist.project.shopList.domain.Item

data class ShoppingScreenState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = true,
    val selectedPage: Int = 0,
    val errorMessage:UiText? = null,
    val key:String? = null
)