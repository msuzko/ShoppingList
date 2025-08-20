package com.mec.shopping.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mec.shopping.dao.ShoppingEventDao
import com.mec.shopping.dao.ShoppingItemDao
import com.mec.shopping.dao.entity.ShoppingEvent
import com.mec.shopping.dao.entity.ShoppingItem

@Database(entities = [ShoppingEvent::class, ShoppingItem::class], version = 2, exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun shoppingEventDao(): ShoppingEventDao
    abstract fun shoppingItemDao(): ShoppingItemDao

    companion object {
        const val DATABASE_NAME = "shopping_database" //NON-NLS

        @Volatile
        private var Instance: ShoppingDatabase? = null

        fun getInstance(context: Context): ShoppingDatabase {
            return Instance ?: synchronized(this) {
                Instance ?: Room.databaseBuilder(
                    context = context,
                    klass = ShoppingDatabase::class.java,
                    name = DATABASE_NAME
                )
                    .addMigrations(MIGRATION_1_2)
                    .build().also { Instance = it }
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(M_1_2_CREATE_NEW_TABLE.trimIndent())
                db.execSQL(M_1_2_COPY_DATA_TO_NEW_TABLE.trimIndent())
                db.execSQL("DROP TABLE shopping_item")//NON-NLS
                db.execSQL("ALTER TABLE shopping_item_new RENAME TO shopping_item")//NON-NLS
            }
        }
    }
}