package com.mec.shopping.ui.event.details

import com.mec.shopping.ui.event.add.AddEventDetails

data class EventDetailsUiState(
    val eventDetails: AddEventDetails = AddEventDetails(),
    val itemList: List<ItemUiState> = emptyList()
)

data class ItemDetails(
    val id: Long = 0L,
    val eventId: Long = 0L,
    val name: String = "",
    val price: String = "",
    val quantity: String = ""
)

data class ItemUiState(
    val isEdit: Boolean = false,
    val itemDetails: ItemDetails = ItemDetails(),
)