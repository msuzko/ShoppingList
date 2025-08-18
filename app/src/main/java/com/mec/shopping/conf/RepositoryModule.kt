package com.mec.shopping.conf

import com.mec.shopping.dao.repository.ShoppingEventRepository
import com.mec.shopping.dao.repository.ShoppingEventRepositoryImpl
import com.mec.shopping.dao.repository.ShoppingItemRepository
import com.mec.shopping.dao.repository.ShoppingItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindShoppingEventRepository(impl: ShoppingEventRepositoryImpl): ShoppingEventRepository

    @Binds
    abstract fun bindShoppingItemRepository(impl: ShoppingItemRepositoryImpl): ShoppingItemRepository

}