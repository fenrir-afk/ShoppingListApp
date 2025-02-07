package org.shoplist.project.shopList.presentation

import org.shoplist.project.core.domain.DataError


sealed interface OneTimeEvent {
    data class Error(val error:DataError):OneTimeEvent
}