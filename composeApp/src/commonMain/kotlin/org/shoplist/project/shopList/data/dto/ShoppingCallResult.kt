package org.shoplist.project.shopList.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShoppingCallResult(
    @SerialName("success") var success: Boolean,
)