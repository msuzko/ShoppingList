package com.mec.shopping.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mec.shopping.dao.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Insert
    suspend fun insert(event: ShoppingItem)

    @Update
    suspend fun update(event: ShoppingItem)

    @Delete
    suspend fun delete(event: ShoppingItem)

    @Query("SELECT * FROM shopping_item")//NON-NLS
    fun getAllEvents(): Flow<List<ShoppingItem>>
}