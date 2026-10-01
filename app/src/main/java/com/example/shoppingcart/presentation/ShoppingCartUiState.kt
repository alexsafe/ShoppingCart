package com.example.shoppingcart.presentation

import com.example.shoppingcart.domain.CartLineCalculation
import java.math.BigDecimal

data class ShoppingCartUiState(
    val lineItems: List<CartLineCalculation> = emptyList(),
    val discountedSubtotal: BigDecimal = BigDecimal.ZERO.setScale(2),
    val salesTax: BigDecimal = BigDecimal.ZERO.setScale(2),
    val total: BigDecimal = BigDecimal.ZERO.setScale(2),
)
