package com.example.shoppingcart.data

import com.example.shoppingcart.domain.CartItem
import com.example.shoppingcart.domain.Category
import java.math.BigDecimal

class InMemoryCartRepository : CartRepository {
    override fun getItems(): List<CartItem> = listOf(
        CartItem("Organic apples", BigDecimal("2.49"), 3, Category.FOOD),
        CartItem("Cotton shirt", BigDecimal("24.99"), 1, Category.CLOTHING),
        CartItem("Wireless headphones", BigDecimal("79.99"), 1, Category.ELECTRONICS),
    )
}
