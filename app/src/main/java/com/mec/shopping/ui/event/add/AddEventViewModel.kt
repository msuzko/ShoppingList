package com.mec.shopping.ui.event.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mec.shopping.repository.ShoppingEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(shoppingEventRepository: ShoppingEventRepository): ViewModel() {
    var addEventUiState by mutableStateOf(AddEventUiState())

    fun updateUiState(addEventDetails: AddEventDetails) {
        this.addEventUiState = AddEventUiState(
            addEventDetails = addEventDetails,
            isEntryValid = validateInput(addEventDetails)
        )

    }

    private fun validateInput(eventDetails: AddEventDetails = addEventUiState.addEventDetails): Boolean {
        return with(eventDetails) {
            name.isNotBlank() && eventDate.isNotBlank()
        }
    }

    suspend fun saveEvent() {
        if (validateInput()) {
//            shoppingEventRepository.insertEvent(addEventUiState.addEventDetails.toShoppingEvent())
        }
    }


}