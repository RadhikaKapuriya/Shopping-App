package com.example.techtrader.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.techtrader.data.model.CartItems

@Database(version = 1, entities = [CartItems::class])

abstract class ProductDatabase : RoomDatabase(){

    abstract fun getCartDao(): CartDao

    companion object{
        @Volatile
        private var instance: ProductDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ProductDatabase::class.java, "ProductDatabase.db").build()

    }
}