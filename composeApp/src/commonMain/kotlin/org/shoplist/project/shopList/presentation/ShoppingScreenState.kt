package org.shoplist.project.shopList.presentation

import org.shoplist.project.core.presentation.UiText
import org.shoplist.project.shopList.domain.Item
import org.shoplist.project.shopList.domain.ListsItem

data class ShoppingScreenState(
    val listsContent: MutableList<MutableList<Item>> = mutableListOf(),
    val itemLists: List<ListsItem> = emptyList(),
    val isLoading: Boolean = true,
    val selectedPage: Int = 0,
    val errorMessage:UiText? = null,
    val key:String? = null
)