package org.shoplist.project.shopList.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListDto(
    @SerialName("created") var created: String,
    @SerialName("name") var name: String,
    @SerialName("id") var id:Int,
)