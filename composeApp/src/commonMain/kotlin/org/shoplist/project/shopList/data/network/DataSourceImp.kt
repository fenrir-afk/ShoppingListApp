package org.shoplist.project.shopList.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.shoplist.project.core.domain.Result
import io.ktor.client.request.parameter
import org.shoplist.project.core.data.safeCall
import org.shoplist.project.core.domain.DataError
import org.shoplist.project.shopList.data.dto.ItemsDto
import org.shoplist.project.shopList.data.dto.ListDto
import org.shoplist.project.shopList.data.dto.ListsDto
import org.shoplist.project.shopList.data.dto.ShoppingCallResult

private const val BASE_URL = "https://cyberprot.ru/shopping/v2"

class DataSourceImp(
    private val httpClient: HttpClient
): DataSource {

    override suspend fun getList(
        listId: Int
    ): Result<ItemsDto, DataError.Remote> {
        return safeCall<ItemsDto> {
            httpClient.get(
                urlString = "$BASE_URL/GetShoppingList"
            ) {
                parameter("list_id", listId)
            }
        }
    }

    override suspend fun getAllLists(key: String): Result<ListsDto, DataError.Remote> {
        return safeCall<ListsDto> {
            httpClient.get(
                urlString = "$BASE_URL/GetAllMyShopLists"
            ) {
                parameter("key", key)
            }
        }
    }

    override suspend fun createKey(): Result<String, DataError.Remote> {
        return safeCall<String>() {
            httpClient.get(
                urlString = "$BASE_URL/CreateTestKey"
            )
        }
    }

    override suspend fun checkKey(key: String): Result<ShoppingCallResult, DataError.Remote> {
        return safeCall<ShoppingCallResult>{
            httpClient.get(
                urlString = "$BASE_URL/Authentication"
            ){
                parameter("key", key)
            }
        }
    }

}