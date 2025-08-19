package com.mec.shopping.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mec.shopping.dao.entity.ShoppingEvent
import com.mec.shopping.ui.common.EmptyListSurface
import com.mec.shopping.ui.common.ShoppingAppBar

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navigateToAddEvent: () -> Unit = {},
    navigateToEventDetails: (Long, String) -> Unit,
    viewModule: HomeViewModule = hiltViewModel()
) {
    val uiState by viewModule.homeUiState.collectAsState()
    Scaffold(
        topBar = {
            ShoppingAppBar(
                title = "Shopping Events",
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToAddEvent) {
                Icon(Icons.Filled.Add, contentDescription = "Add Event")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        if (uiState.events.isEmpty()) {
            EmptyListSurface(
                message = "No events found. \nClick the '+' button to add a new event.",
                modifier = modifier.padding(innerPadding)
            )
            return@Scaffold
        } else {
            ShoppingList(
                shoppingEvents = uiState.events,
                navigateToEventDetails = navigateToEventDetails,
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
fun ShoppingList(
    shoppingEvents: List<ShoppingEvent>,
    navigateToEventDetails: (Long, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(shoppingEvents.size) { event ->
            ShoppingEventItem(
                event = shoppingEvents[event],
                modifier = Modifier
                    .fillMaxSize(),
                onTapEvent = navigateToEventDetails
            )
        }
    }
}

@Composable
fun ShoppingEventItem(
    event: ShoppingEvent,
    onTapEvent: (Long, String) -> Unit,
    modifier: Modifier
) {
    ListItem(
        tonalElevation = 10.dp,
        headlineContent = {
            Text(text = event.name, modifier)
        },
        supportingContent = {
            Text(text = event.eventDate)
        },
        trailingContent = {
            Text(
                text = "$${event.totalCost} ",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingContent = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Event")
            }
        },
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onTapEvent(event.id, event.eventDate)
            }
    )
}
