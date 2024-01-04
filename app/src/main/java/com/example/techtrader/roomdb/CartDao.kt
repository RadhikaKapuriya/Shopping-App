package com.example.techtrader.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.techtrader.data.model.CartItems

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItems)

    @Delete
    suspend fun delete(item: CartItems)

    @Query("SELECT * FROM cart_details")
    fun getAllCartItems(): LiveData<List<CartItems>>

    @Query("DELETE FROM cart_details")
    fun deleteAll()
}
