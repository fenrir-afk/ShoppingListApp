package org.shoplist.project.shopList.data.mappers

import org.shoplist.project.shopList.data.dto.ItemsDto
import org.shoplist.project.shopList.domain.Item

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