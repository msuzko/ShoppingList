package com.mec.shopping.dao.repository

import com.mec.shopping.dao.ShoppingEventDao
import com.mec.shopping.dao.entity.ShoppingEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ShoppingEventRepository {
    suspend fun insertEvent(event: ShoppingEvent)

    suspend fun updateEvent(event: ShoppingEvent)

    suspend fun deleteEvent(event: ShoppingEvent)

    fun getAllEvents(): Flow<List<ShoppingEvent>>
}

class ShoppingEventRepositoryImpl @Inject
constructor(val shoppingEventDao: ShoppingEventDao) : ShoppingEventRepository {

    override suspend fun insertEvent(event: ShoppingEvent) = shoppingEventDao.insert(event)

    override suspend fun updateEvent(event: ShoppingEvent) = shoppingEventDao.update(event)

    override suspend fun deleteEvent(event: ShoppingEvent) = shoppingEventDao.delete(event)

    override fun getAllEvents(): Flow<List<ShoppingEvent>> = shoppingEventDao.getAllEvents()

}