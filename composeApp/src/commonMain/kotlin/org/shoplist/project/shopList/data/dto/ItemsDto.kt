package org.shoplist.project.shopList.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemsDto (
  @SerialName("success" ) var success  : Boolean?  = null,
  @SerialName("item_list") var itemDtoList : ArrayList<ItemDto> = arrayListOf()
)