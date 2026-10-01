package com.example.shoppingcart.data

import com.example.shoppingcart.domain.CartItem

interface CartRepository {
    fun getItems(): List<CartItem>
}
