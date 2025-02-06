package org.shoplist.project.shopList.data.network

import org.shoplist.project.core.domain.DataError
import org.shoplist.project.shopList.data.dto.ItemsDto
import org.shoplist.project.core.domain.Result
import org.shoplist.project.shopList.data.dto.ListDto
import org.shoplist.project.shopList.data.dto.ListsDto
import org.shoplist.project.shopList.data.dto.ShoppingCallResult

interface DataSource {
    suspend fun getList(
        listId: Int
    ): Result<ItemsDto, DataError.Remote>

    suspend fun getAllLists(
        key: String
    ): Result<ListsDto, DataError.Remote>

    suspend fun createKey(): Result<String, DataError.Remote>

    suspend fun checkKey(key:String): Result<ShoppingCallResult, DataError.Remote>
}