package org.shoplist.project.shopList.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListsDto (
    @SerialName("success" ) var success  : Boolean?  = null,
    @SerialName("shop_list") var listsDto : ArrayList<ListDto> = arrayListOf()
)