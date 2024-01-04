package com.example.techtrader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techtrader.roomdb.CartRepository

class CartViewModelFactory(private val repository: CartRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return CartViewModel(repository) as T
    }
}