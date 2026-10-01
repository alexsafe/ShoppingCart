package com.example.shoppingcart.domain

import com.google.common.truth.Truth.assertThat
import java.math.BigDecimal
import org.junit.Test

class CartItemTest {
    @Test
    fun `rejects blank names negative prices and non-positive quantities`() {
        val blankNameError = captureIllegalArgument { CartItem(" ", BigDecimal.ONE, 1, Category.FOOD) }
        val negativePriceError = captureIllegalArgument { CartItem("Item", BigDecimal("-0.01"), 1, Category.FOOD) }
        val zeroError = captureIllegalArgument { CartItem("Item", BigDecimal.ONE, 0, Category.FOOD) }
        val negativeError = captureIllegalArgument { CartItem("Item", BigDecimal.ONE, -1, Category.FOOD) }

        assertThat(blankNameError).hasMessageThat().contains("name")
        assertThat(negativePriceError).hasMessageThat().contains("price")
        assertThat(zeroError).hasMessageThat().contains("Quantity")
        assertThat(negativeError).hasMessageThat().contains("Quantity")
    }

    private fun captureIllegalArgument(block: () -> Unit): IllegalArgumentException =
        try {
            block()
            throw AssertionError("Expected IllegalArgumentException")
        } catch (error: IllegalArgumentException) {
            error
        }
}
