package com.mec.shopping.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mec.shopping.dao.repository.ShoppingEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModule @Inject constructor(
    private val shoppingEventRepository: ShoppingEventRepository
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {
            shoppingEventRepository.getAllEvents().collect { events ->
                _homeUiState.update {
                    it.copy(events = events)
                }
            }
        }
    }
}