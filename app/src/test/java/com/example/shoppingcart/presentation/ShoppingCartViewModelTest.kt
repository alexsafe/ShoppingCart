package com.example.shoppingcart.presentation

import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.domain.CartCalculator
import com.example.shoppingcart.domain.CartItem
import com.example.shoppingcart.domain.Category
import com.google.common.truth.Truth.assertThat
import java.math.BigDecimal
import org.junit.Test

class ShoppingCartViewModelTest {
    @Test
    fun `exposes repository items and calculated totals`() {
        val repository = FakeCartRepository(
            listOf(
                CartItem("Milk", BigDecimal("10.00"), 2, Category.FOOD),
                CartItem("Jacket", BigDecimal("20.00"), 1, Category.CLOTHING),
            ),
        )

        val state = ShoppingCartViewModel(repository, CartCalculator()).uiState.value

        assertThat(state.lineItems.map { it.item.name }).containsExactly("Milk", "Jacket").inOrder()
        assertThat(state.discountedSubtotal).isEqualTo(money("35.00"))
        assertThat(state.salesTax).isEqualTo(money("2.98"))
        assertThat(state.total).isEqualTo(money("37.98"))
    }

    private fun money(value: String) = BigDecimal(value).setScale(2)

    private class FakeCartRepository(private val items: List<CartItem>) : CartRepository {
        override fun getItems(): List<CartItem> = items
    }
}
