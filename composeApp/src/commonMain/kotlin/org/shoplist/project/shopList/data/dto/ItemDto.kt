package org.shoplist.project.shopList.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ItemDto(
  @SerialName("created") var created: String,
  @SerialName("name") var name: String,
  @SerialName("is_crossed") var isCrossed : Boolean,
  @SerialName("id") var id:Int,
)