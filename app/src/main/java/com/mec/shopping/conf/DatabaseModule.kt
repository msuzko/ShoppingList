package com.mec.shopping.conf

import android.content.Context
import com.mec.shopping.dao.ShoppingEventDao
import com.mec.shopping.dao.ShoppingItemDao
import com.mec.shopping.database.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideShoppingEventDao(@ApplicationContext context: Context): ShoppingEventDao {
        return ShoppingDatabase.getInstance(context).shoppingEventDao()
    }

    @Provides
    fun provideShoppingItemDao(@ApplicationContext context: Context): ShoppingItemDao {
        return ShoppingDatabase.getInstance(context).shoppingItemDao()
    }
}