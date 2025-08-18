package com.mec.shopping.ui.home

import com.mec.shopping.dao.entity.ShoppingEvent

data class HomeUiState(
    val events: List<ShoppingEvent> = emptyList()
)