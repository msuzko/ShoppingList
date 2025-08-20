package com.mec.shopping.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_event")//NON-NLS
data class ShoppingEvent(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "initial_budget") val initialBudget: Double,//NON-NLS
    @ColumnInfo(name = "total_cost") val totalCost: Double,//NON-NLS
    @ColumnInfo(name = "event_date") val eventDate: String,//NON-NLS
    val completed: Boolean
)