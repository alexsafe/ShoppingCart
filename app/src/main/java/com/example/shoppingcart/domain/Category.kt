package com.example.shoppingcart.domain

import java.math.BigDecimal

enum class Category(val discountRate: BigDecimal) {
    FOOD(BigDecimal("0.10")),
    CLOTHING(BigDecimal("0.15")),
    ELECTRONICS(BigDecimal("0.05")),
}
