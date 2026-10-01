package com.example.shoppingcart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.domain.CartCalculator

class ShoppingCartViewModelFactory(
    private val repository: CartRepository,
    private val calculator: CartCalculator,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(ShoppingCartViewModel::class.java))
        return ShoppingCartViewModel(repository, calculator) as T
    }
}
