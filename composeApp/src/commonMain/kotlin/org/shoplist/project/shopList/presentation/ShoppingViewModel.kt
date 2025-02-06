package org.shoplist.project.shopList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.shoplist.project.core.domain.onError
import org.shoplist.project.core.domain.onSuccess
import org.shoplist.project.core.presentation.toUiText
import org.shoplist.project.shopList.domain.Item
import org.shoplist.project.shopList.domain.repository.MainRepository

class ShoppingViewModel(
    private val repository: MainRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(ShoppingScreenState())
    val state = _state
    private val apiKey:String? = null
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