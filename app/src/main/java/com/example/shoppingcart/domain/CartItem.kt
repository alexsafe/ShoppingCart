package com.example.shoppingcart.domain

import java.math.BigDecimal

data class CartItem(
    val name: String,
    val unitPrice: BigDecimal,
    val quantity: Int,
    val category: Category,
) {
    init {
        require(name.isNotBlank()) { "Item name cannot be blank." }
        require(unitPrice >= BigDecimal.ZERO) { "Unit price cannot be negative." }
        require(quantity > 0) { "Quantity must be positive." }
    }
}
