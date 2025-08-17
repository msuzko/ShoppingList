package com.mec.shopping.data.entity

import java.sql.Date

data class ShoppingEvent(
    val id: Long = 0,
    val name: String,
    val initialBudget: Double = 0.0,
    val totalCost: Double = 0.0,
    val eventDate: Date,
    val completed: Boolean = false
)