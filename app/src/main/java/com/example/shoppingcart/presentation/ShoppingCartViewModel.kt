package com.example.shoppingcart.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppingcart.data.CartRepository
import com.example.shoppingcart.domain.CartCalculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoppingCartViewModel(
    repository: CartRepository,
    calculator: CartCalculator,
) : ViewModel() {
    private val calculation = calculator.calculate(repository.getItems())

    private val _uiState = MutableStateFlow(
        ShoppingCartUiState(
            lineItems = calculation.lines,
            discountedSubtotal = calculation.discountedSubtotal,
            salesTax = calculation.salesTax,
            total = calculation.total,
        ),
    )
    val uiState: StateFlow<ShoppingCartUiState> = _uiState.asStateFlow()
}
