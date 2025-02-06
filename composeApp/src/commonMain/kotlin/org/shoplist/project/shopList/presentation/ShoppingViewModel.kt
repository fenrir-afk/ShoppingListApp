package org.shoplist.project.shopList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.shoplist.project.core.domain.onError
import org.shoplist.project.core.domain.onSuccess
import org.shoplist.project.core.presentation.toUiText
import org.shoplist.project.shopList.domain.repository.MainRepository

class ShoppingViewModel(
    private val repository: MainRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(ShoppingScreenState())
    val state = _state.asStateFlow()


    //private var cachedBooks = emptyList<Book>()

    fun createKey() = viewModelScope.launch{
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        repository.createKey().onSuccess { key ->
            _state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    key = key
                )
            }
        }.onError { error ->
            _state.update {
                it.copy(
                    items = emptyList(),
                    isLoading = false,
                    errorMessage = error.toUiText()
                )
            }
        }
    }
    fun checkKey(key:String) = viewModelScope.launch{
       repository.checkKey(key).onSuccess {

       }.onError {
       }
    }

    fun getAllLists(key:String) = viewModelScope.launch {
        var list = repository.getAllLists(key)
        println(list)
    }

    fun getList(id: Int) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        repository
            .getShoppingList(id)
            .onSuccess { resultList ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        items = resultList
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        items = emptyList(),
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
    }

}