package com.example.techtrader.roomdb

import com.example.techtrader.data.model.CartItems


class CartRepository(private val db: ProductDatabase) {

    suspend fun insert(item: CartItems) = db.getCartDao().insert(item)
    suspend fun delete(item: CartItems) = db.getCartDao().delete(item)
    suspend fun deleteAll() = db.getCartDao().deleteAll()
    fun allCartItems() = db.getCartDao().getAllCartItems()
}