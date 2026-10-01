package com.example.shoppingcart.domain

import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP

class CartCalculator {
    fun calculate(items: List<CartItem>): CartCalculation {
        val lines = items.map(::calculateLine)
        val subtotal = lines.sumOf { it.discountedAmount }.money()
        val salesTax = subtotal.multiply(SALES_TAX_RATE).money()

        return CartCalculation(
            lines = lines,
            discountedSubtotal = subtotal,
            salesTax = salesTax,
            total = subtotal.add(salesTax).money(),
        )
    }

    private fun calculateLine(item: CartItem): CartLineCalculation {
        val preDiscountAmount = item.unitPrice.multiply(item.quantity.toBigDecimal()).money()
        val discount = preDiscountAmount.multiply(item.category.discountRate).money()

        return CartLineCalculation(
            item = item,
            preDiscountAmount = preDiscountAmount,
            discount = discount,
            discountedAmount = preDiscountAmount.subtract(discount).money(),
        )
    }

    private fun BigDecimal.money(): BigDecimal = setScale(MONEY_SCALE, HALF_UP)

    private companion object {
        const val MONEY_SCALE = 2
        val SALES_TAX_RATE = BigDecimal("0.085")
    }
}
