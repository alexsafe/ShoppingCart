package com.example.shoppingcart.domain

import com.google.common.truth.Truth.assertThat
import java.math.BigDecimal
import org.junit.Test

class CartCalculatorTest {
    private val calculator = CartCalculator()

    @Test
    fun `calculates quantities categories discounted subtotal sales tax and total`() {
        val result = calculator.calculate(
            listOf(
                item(name = "Apples", unitPrice = "2.50", quantity = 3, category = Category.FOOD),
                item(name = "Shirt", unitPrice = "20.00", category = Category.CLOTHING),
                item(name = "Headphones", unitPrice = "30.00", category = Category.ELECTRONICS),
            ),
        )

        assertThat(result.lines.map { it.preDiscountAmount })
            .containsExactly(money("7.50"), money("20.00"), money("30.00"))
            .inOrder()
        assertThat(result.lines.map { it.discount })
            .containsExactly(money("0.75"), money("3.00"), money("1.50"))
            .inOrder()
        assertThat(result.discountedSubtotal).isEqualTo(money("52.25"))
        assertThat(result.salesTax).isEqualTo(money("4.44"))
        assertThat(result.total).isEqualTo(money("56.69"))
    }

    @Test
    fun `calculates sales tax from the discounted subtotal`() {
        val result = calculator.calculate(
            listOf(item(unitPrice = "100.00", category = Category.FOOD)),
        )

        assertThat(result.discountedSubtotal).isEqualTo(money("90.00"))
        assertThat(result.salesTax).isEqualTo(money("7.65"))
    }

    @Test
    fun `rounds an exact half-cent discount up`() {
        val result = calculator.calculate(
            listOf(item(unitPrice = "0.05", category = Category.FOOD)),
        )

        assertThat(result.lines.single().discount).isEqualTo(money("0.01"))
    }

    @Test
    fun `returns zero amounts for an empty cart`() {
        val result = calculator.calculate(emptyList())

        assertThat(result.lines).isEmpty()
        assertThat(result.discountedSubtotal).isEqualTo(money("0.00"))
        assertThat(result.salesTax).isEqualTo(money("0.00"))
        assertThat(result.total).isEqualTo(money("0.00"))
    }

    private fun item(
        name: String = "Item",
        unitPrice: String = "100.00",
        quantity: Int = 1,
        category: Category,
    ) = CartItem(name, BigDecimal(unitPrice), quantity, category)

    private fun money(value: String) = BigDecimal(value).setScale(2)
}
