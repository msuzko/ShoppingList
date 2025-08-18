package com.mec.shopping.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mec.shopping.dao.ShoppingEventDao
import com.mec.shopping.dao.ShoppingItemDao
import com.mec.shopping.dao.entity.ShoppingEvent
import com.mec.shopping.dao.entity.ShoppingItem

@Database(entities = [ShoppingEvent::class, ShoppingItem::class], version = 1, exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun shoppingEventDao(): ShoppingEventDao
    abstract fun shoppingItemDao(): ShoppingItemDao

    companion object {
        const val DATABASE_NAME = "shopping_database"

        @Volatile
        private var Instance: ShoppingDatabase? = null

        fun getInstance(context: Context): ShoppingDatabase {
            return Instance ?: synchronized(this) {
                Instance ?: Room.databaseBuilder(
                    context = context,
                    klass = ShoppingDatabase::class.java,
                    name = DATABASE_NAME
                ).build().also {
                    Instance = it
                }
            }
        }
    }
}