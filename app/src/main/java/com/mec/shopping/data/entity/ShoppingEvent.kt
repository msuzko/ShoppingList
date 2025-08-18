package com.mec.shopping.data.entity

data class ShoppingEvent(
    val id: Long,
    val name: String,
    val initialBudget: Double,
    val totalCost: Double,
    val eventDate: String,
    val completed: Boolean
)