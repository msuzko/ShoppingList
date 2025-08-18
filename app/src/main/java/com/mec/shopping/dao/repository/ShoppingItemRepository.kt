package com.mec.shopping.dao.repository

import com.mec.shopping.dao.ShoppingItemDao
import com.mec.shopping.dao.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ShoppingItemRepository {
    suspend fun insertEvent(event: ShoppingItem)

    suspend fun updateEvent(event: ShoppingItem)

    suspend fun deleteEvent(event: ShoppingItem)

    fun getAllEvents(): Flow<List<ShoppingItem>>
}

class ShoppingItemRepositoryImpl @Inject
constructor(val shoppingItemDao: ShoppingItemDao) : ShoppingItemRepository {

    override suspend fun insertEvent(event: ShoppingItem) = shoppingItemDao.insert(event)

    override suspend fun updateEvent(event: ShoppingItem) = shoppingItemDao.update(event)

    override suspend fun deleteEvent(event: ShoppingItem) = shoppingItemDao.delete(event)

    override fun getAllEvents(): Flow<List<ShoppingItem>> = shoppingItemDao.getAllEvents()
}