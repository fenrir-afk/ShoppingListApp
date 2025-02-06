package org.shoplist.project.shopList.data.mappers

import org.shoplist.project.shopList.data.dto.ItemsDto
import org.shoplist.project.shopList.data.dto.ListsDto
import org.shoplist.project.shopList.domain.Item
import org.shoplist.project.shopList.domain.ListsItem

fun ItemsDto.toItemList(): List<Item> {
    return itemDtoList.map {
        Item(
            id = it.id,
            created = it.created,
            name = it.name,
            crossed = it.isCrossed
        )
    }
}

fun ListsDto.toListsItem(): List<ListsItem> {
    return listsDto.map {
        ListsItem(
            id = it.id,
            created = it.created,
            name = it.name
        )
    }
}