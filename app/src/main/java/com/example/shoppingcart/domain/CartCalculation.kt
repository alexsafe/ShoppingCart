package com.example.shoppingcart.domain

import java.math.BigDecimal

data class CartLineCalculation(
    val item: CartItem,
    val preDiscountAmount: BigDecimal,
    val discount: BigDecimal,
    val discountedAmount: BigDecimal,
)

data class CartCalculation(
    val lines: List<CartLineCalculation>,
    val discountedSubtotal: BigDecimal,
    val salesTax: BigDecimal,
    val total: BigDecimal,
)
