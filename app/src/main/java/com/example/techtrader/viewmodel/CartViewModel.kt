package com.example.techtrader.viewmodel

import androidx.lifecycle.ViewModel
import com.example.techtrader.roomdb.CartRepository
import com.example.techtrader.data.model.CartItems
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    fun insert(item: CartItems) = GlobalScope.launch {
        repository.insert(item)
    }

    fun delete(item: CartItems) = GlobalScope.launch {
        repository.delete(item)
    }

    fun deleteAll() = GlobalScope.launch {
        repository.deleteAll()
    }

    fun allCartItems() = repository.allCartItems()
}