package com.mec.shopping.conf

import com.mec.shopping.repository.ShoppingEventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HiltModules {

    @Provides
    fun provideShoppingEventRepository(): ShoppingEventRepository {
        return ShoppingEventRepository()
    }
}