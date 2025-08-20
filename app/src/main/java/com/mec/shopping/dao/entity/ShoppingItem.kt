package com.mec.shopping.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "shopping_item",//NON-NLS
    foreignKeys = [
        ForeignKey(
            entity = ShoppingEvent::class,
            parentColumns = ["id"],
            childColumns = ["event_id"],//NON-NLS
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id") val id: Long = 0,//NON-NLS
    @ColumnInfo(name = "event_id") val eventId: Long,//NON-NLS
    @ColumnInfo(name = "item_name") val name: String,//NON-NLS
    val quantity: Double = 1.0,
    val price: Double = 0.0
)
