package com.mec.shopping.ui.event

import com.mec.shopping.dao.entity.ShoppingEvent
import com.mec.shopping.dao.entity.ShoppingItem
import com.mec.shopping.ui.event.add.AddEventDetails
import com.mec.shopping.ui.event.details.ItemDetails

fun ShoppingEvent.toAddEventDetails(): AddEventDetails = AddEventDetails(
    id = id,
    name = name,
    initialBudget = initialBudget.toString(),
    totalCost = totalCost,
    eventDate = eventDate,
    completed = completed
)

fun ShoppingItem.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    eventId = eventId,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)

fun ItemDetails.toShoppingItem(): ShoppingItem = ShoppingItem(
    id = id,
    eventId = eventId,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toDoubleOrNull() ?: 1.0
)