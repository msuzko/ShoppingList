package com.mec.shopping.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mec.shopping.dao.entity.ShoppingEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingEventDao {
    @Insert
    suspend fun insert(event: ShoppingEvent)

    @Update
    suspend fun update(event: ShoppingEvent)

    @Delete
    suspend fun delete(event: ShoppingEvent)

    @Query("SELECT * FROM shopping_event")
    fun getAllEvents(): Flow<List<ShoppingEvent>>
}