package org.shoplist.project.shopList.domain.repository

import org.shoplist.project.core.domain.DataError
import org.shoplist.project.core.domain.Result
import org.shoplist.project.shopList.domain.Item
import org.shoplist.project.shopList.domain.ListsItem


interface MainRepository {
    suspend fun getShoppingList(id: Int): Result<List<Item>, DataError.Remote>

    suspend fun getAllLists(key: String): Result<List<ListsItem>, DataError.Remote>

    suspend fun createKey(): Result<String, DataError.Remote>

    suspend fun checkKey(key:String): Result<Boolean, DataError.Remote>
}