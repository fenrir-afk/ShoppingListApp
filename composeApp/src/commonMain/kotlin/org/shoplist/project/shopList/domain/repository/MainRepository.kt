package org.shoplist.project.shopList.domain.repository

import org.shoplist.project.core.domain.DataError
import org.shoplist.project.core.domain.Result
import org.shoplist.project.shopList.domain.Item


interface MainRepository {
    suspend fun getShoppingList(id: Int): Result<List<Item>, DataError.Remote>
}