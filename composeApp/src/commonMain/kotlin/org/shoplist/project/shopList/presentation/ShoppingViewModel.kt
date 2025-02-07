package org.shoplist.project.shopList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.shoplist.project.core.domain.DataError
import org.shoplist.project.core.domain.onError
import org.shoplist.project.core.domain.onSuccess
import org.shoplist.project.core.presentation.toUiText
import org.shoplist.project.shopList.domain.repository.MainRepository

class ShoppingViewModel(
    private val repository: MainRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(ShoppingScreenState())
    val state = _state.asStateFlow()

    private val _events = Channel<OneTimeEvent>()
    val event = _events.receiveAsFlow()

    fun onAction(action: ShoppingActions){
        when(action){
            is ShoppingActions.OnCheckKeyAction -> {
                checkKey(action.key)
            }

            ShoppingActions.OnCreateKeyAction -> {
                createKey()
            }
        }
    }

    private fun createKey() = viewModelScope.launch{
        repository.createKey().onSuccess { key ->
            _state.update {
                it.copy(
                    errorMessage = null,
                    key = key
                )
            }
        }.onError { error ->
            _state.update {
                it.copy(
                    errorMessage = error.toUiText()
                )
            }
            _events.send(OneTimeEvent.Error(DataError.Remote.UNKNOWN))
        }
    }
    private fun checkKey(key:String) = viewModelScope.launch{
       repository.checkKey(key).onSuccess {
           _state.update {
               it.copy(
                   errorMessage = null,
                   key = key
               )
           }
       }.onError { error ->
           _state.update {
               it.copy(
                   itemLists = emptyList(),
                   errorMessage = error.toUiText()
               )
           }
           _events.send(OneTimeEvent.Error(DataError.Remote.WRONG_KEY))
       }
    }



    fun getAllLists(key:String) = viewModelScope.launch {
        repository.getAllLists(key).onSuccess { lists ->
            _state.update {
                it.copy(
                    itemLists = lists,
                    errorMessage = null,
                    key = key
                )
            }
        }.onError { error ->
            _state.update {
                it.copy(
                    itemLists = emptyList(),
                    errorMessage = error.toUiText()
                )
            }
            _events.send(OneTimeEvent.Error(DataError.Remote.WRONG_KEY))
        }
    }

    fun getListsContent() = viewModelScope.launch {
        if(state.value.itemLists.isNotEmpty()){
            state.value.itemLists.forEachIndexed { index, listsItem ->
                repository
                    .getShoppingList(listsItem.id)
                    .onSuccess { resultList ->
                        _state.value.listsContent.add(resultList.toMutableList())
                    }
                    .onError { error ->
                        _events.send(OneTimeEvent.Error(error))
                    }
            }
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }else{
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}