package org.shoplist.project.shopList.data.repository

import org.shoplist.project.core.domain.DataError
import org.shoplist.project.core.domain.Result
import org.shoplist.project.core.domain.map
import org.shoplist.project.shopList.data.mappers.toItemList
import org.shoplist.project.shopList.data.mappers.toListsItem
import org.shoplist.project.shopList.data.network.DataSource
import org.shoplist.project.shopList.domain.Item
import org.shoplist.project.shopList.domain.ListsItem
import org.shoplist.project.shopList.domain.repository.MainRepository

class MainRepositoryImp(private val dataSource: DataSource):MainRepository {
    override suspend fun getShoppingList(id: Int): Result<List<Item>, DataError.Remote> {
        return dataSource.getList(id).map { it.toItemList() }
    }

    override suspend fun getAllLists(key: String): Result<List<ListsItem>, DataError.Remote> {
        return dataSource.getAllLists(key).map { it.toListsItem() }
    }

    override suspend fun createKey(): Result<String, DataError.Remote> {
        return dataSource.createKey()
    }

    override suspend fun checkKey(key:String): Result<Boolean, DataError.Remote> {
        return dataSource.checkKey(key).map {
            it.success
        }
    }

}