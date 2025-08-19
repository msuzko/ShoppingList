package com.mec.shopping.ui.event.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mec.shopping.dao.entity.ShoppingEvent
import com.mec.shopping.dao.entity.ShoppingItem
import com.mec.shopping.dao.repository.ShoppingEventRepository
import com.mec.shopping.dao.repository.ShoppingItemRepository
import com.mec.shopping.destinations.EventDetailsRoute
import com.mec.shopping.ui.event.add.AddEventDetails
import com.mec.shopping.ui.event.toAddEventDetails
import com.mec.shopping.ui.event.toItemDetails
import com.mec.shopping.ui.event.toShoppingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val shoppingEventRepository: ShoppingEventRepository,
    private val itemRepository: ShoppingItemRepository
) : ViewModel() {
    private val detailsRoute: EventDetailsRoute = savedStateHandle.toRoute<EventDetailsRoute>()
    private val _eventDetailUiState = MutableStateFlow(EventDetailsUiState())
    val eventDetailUiState = _eventDetailUiState.asStateFlow()

    suspend fun addItem() {
        val item = ShoppingItem(eventId = detailsRoute.id, name = "Item")
        itemRepository.insertEvent(item)
    }

    init {
        viewModelScope.launch {
            shoppingEventRepository.getEventAndItems(detailsRoute.id)
                .collect { map -> updateEventDetailsUiState(map) }
        }
    }

    private fun updateEventDetailsUiState(map: Map<ShoppingEvent, List<ShoppingItem>>) {
        _eventDetailUiState.update { it ->
            val entry = map.entries.firstOrNull()
            val event = entry?.key?.toAddEventDetails()
                ?: AddEventDetails(id = detailsRoute.id, name = detailsRoute.name)
            val itemList =
                entry?.value?.map { item -> ItemUiState(itemDetails = item.toItemDetails()) }
                    ?: emptyList()
            it.copy(eventDetails = event, itemList = itemList)
        }
    }

    fun enableEditMode(itemDetails: ItemDetails) {
        _eventDetailUiState.update { state ->
            state.copy(itemList = state.itemList.map {
                if (it.itemDetails.id == itemDetails.id) {
                    it.copy(isEdit = true)
                } else {
                    it
                }
            })
        }
    }

    fun updateItemUiState(itemDetails: ItemDetails) {
        _eventDetailUiState.update { state ->
            state.copy(itemList = state.itemList.map {
                if (it.itemDetails.id == itemDetails.id) {
                    it.copy(itemDetails = itemDetails)
                } else {
                    it
                }
            })
        }
    }

    suspend fun updateItem(itemDetails: ItemDetails) {
        itemRepository.updateEvent(itemDetails.toShoppingItem())
    }
}