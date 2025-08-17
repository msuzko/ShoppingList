package com.mec.shopping.data.entity

data class ShoppingItem(
    val id: Long = 0,
    val eventId: Long,
    val name: String,
    val quantity: Double = 1.0,
    val price: Double = 0.0
)
