package com.mec.shopping.dao.repository

import com.mec.shopping.dao.ShoppingItemDao
import com.mec.shopping.dao.entity.ShoppingItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ShoppingItemRepository {
    suspend fun insertItem(event: ShoppingItem)

    suspend fun updateItem(event: ShoppingItem)

    suspend fun deleteItem(event: ShoppingItem)

    fun getAllItems(): Flow<List<ShoppingItem>>
}

class ShoppingItemRepositoryImpl @Inject
constructor(val shoppingItemDao: ShoppingItemDao) : ShoppingItemRepository {

    override suspend fun insertItem(event: ShoppingItem) = shoppingItemDao.insert(event)

    override suspend fun updateItem(event: ShoppingItem) = shoppingItemDao.update(event)

    override suspend fun deleteItem(event: ShoppingItem) = shoppingItemDao.delete(event)

    override fun getAllItems(): Flow<List<ShoppingItem>> = shoppingItemDao.getAllEvents()
}