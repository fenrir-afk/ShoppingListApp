package org.shoplist.project.shopList.data.network

import org.shoplist.project.core.domain.DataError
import org.shoplist.project.shopList.data.dto.ItemsDto
import org.shoplist.project.core.domain.Result

interface DataSource {
    suspend fun getList(
        listId: Int
    ): Result<ItemsDto, DataError.Remote>

}