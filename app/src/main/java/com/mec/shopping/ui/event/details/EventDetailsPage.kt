package com.mec.shopping.ui.event.details

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mec.shopping.ui.common.DismissibleItem
import com.mec.shopping.ui.common.EditListItem
import com.mec.shopping.ui.common.EmptyListSurface
import com.mec.shopping.ui.common.ShoppingAppBar
import com.mec.shopping.ui.event.add.AddEventDetails
import kotlinx.coroutines.launch

@Composable
fun EventDetailsPage(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EventDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.eventDetailUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            ShoppingAppBar(
                title = "Event Details",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                coroutineScope.launch {
                    viewModel.addItem()
                    if (uiState.itemList.isNotEmpty()) {
                        listState.scrollToItem(uiState.itemList.size - 1)
                    }
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
                Text(text = "Add Item")
            }
        }, floatingActionButtonPosition = FabPosition.Center

    ) { innerPadding ->
        if (uiState.itemList.isEmpty()) {
            EmptyListSurface(
                message = "No items found. \nClick the '+' button to add a new item.",
                modifier = modifier.padding(innerPadding)
            )
            return@Scaffold
        } else {
            ShoppingItemList(
                eventDetails = uiState.eventDetails,
                itemList = uiState.itemList,
                lazyListState = listState,
                onEditModeChanged = viewModel::enableEditMode,
                onValueChange = viewModel::updateItemUiState,
                onItemUpdate = {
                    coroutineScope.launch {
                        viewModel.updateItem(it)
                    }
                },
                onDeleteItem = {
                    coroutineScope.launch {
                        viewModel.deleteItem(it)
                    }
                },
                modifier = modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ShoppingItemList(
    eventDetails: AddEventDetails,
    itemList: List<ItemUiState>,
    lazyListState: LazyListState,
    onValueChange: (ItemDetails) -> Unit,
    onEditModeChanged: (ItemDetails) -> Unit,
    onItemUpdate: (ItemDetails) -> Unit,
    onDeleteItem: (ItemDetails) -> Unit,
    modifier: Modifier
) {
    LazyColumn(state = lazyListState, modifier = modifier) {
        item {
            ListItem(
                colors = ListItemDefaults.colors(colorScheme.primaryContainer),
                headlineContent = {
                    Text(
                        text = "Budget: $${eventDetails.initialBudget}",
                        style = typography.bodyLarge
                    )
                },
                trailingContent = {
                    Text("$${eventDetails.totalCost}", style = typography.bodyMedium)
                }
            )
        }
        items(itemList, key = { it.itemDetails.id }) { itemUiState ->
            SingleItemView(
                itemUiState = itemUiState,
                onValueChange = onValueChange,
                onItemUpdate = onItemUpdate,
                onEditModeChanged = onEditModeChanged,
                onDeleteItem = onDeleteItem,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun SingleItemView(
    itemUiState: ItemUiState,
    onValueChange: (ItemDetails) -> Unit,
    onItemUpdate: (ItemDetails) -> Unit,
    onEditModeChanged: (ItemDetails) -> Unit,
    onDeleteItem: (ItemDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    val item = itemUiState.itemDetails
    if (itemUiState.isEdit) {
        EditListItem(
            itemDetails = item,
            onValueChange = { onValueChange(it) },
            onItemUpdate = { onItemUpdate(it) },
            modifier = modifier
        )
    } else {
        DismissibleItem(
            onDelete = {
                onDeleteItem(item)
            },

        ) {
            ListItem(
                headlineContent = {
                    Text(text = item.name, modifier = Modifier.padding(4.dp))
                },
                supportingContent = {
                    Text(
                        text = "Quantity: $${item.quantity}",
                        modifier = Modifier.padding(4.dp)
                    )
                },
                trailingContent = {
                    Text(
                        text = "$${item.price}",
                        style = typography.bodyMedium
                    )
                },
                leadingContent = {
                    IconButton(onClick = {
                        onEditModeChanged(item)
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Item")
                    }
                }
            )
        }
    }
}
