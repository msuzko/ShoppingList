package com.mec.shopping.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_event")
data class ShoppingEvent(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "initial_budget") val initialBudget: Double,
    @ColumnInfo(name = "total_cost") val totalCost: Double,
    @ColumnInfo(name = "event_date") val eventDate: String,
    val completed: Boolean
)