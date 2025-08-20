package com.mec.shopping.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mec.shopping.dao.entity.ShoppingEvent
import com.mec.shopping.dao.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingEventDao {
    @Insert
    suspend fun insert(event: ShoppingEvent)

    @Update
    suspend fun update(event: ShoppingEvent)

    @Delete
    suspend fun delete(event: ShoppingEvent)

    @Query(SELECT_ALL_EVENTS)
    fun getAllEvents(): Flow<List<ShoppingEvent>>

    @Query(SELECT_EVENT_AND_ITEMS)
    fun getEventAndItems(id: Long): Flow<Map<ShoppingEvent, List<ShoppingItem>>>
}

const val SELECT_ALL_EVENTS = """
        SELECT event.id, event.name, event.initial_budget, event.event_date, event.completed,
            SUM(item.price * item.quantity)  AS total_cost
        FROM shopping_event event
        LEFT JOIN shopping_item item ON item.event_id = event.id
        GROUP BY event.id
    """

const val SELECT_EVENT_AND_ITEMS = """
        SELECT event.id, event.name, event.initial_budget, event.event_date, event.completed,
            (SELECT SUM(item.price * item.quantity) FROM shopping_item item WHERE item.event_id=:id) AS total_cost,
             item.*
        FROM shopping_event event
            LEFT JOIN shopping_item item ON event.id = item.event_id
        WHERE event.id = :id
        """

