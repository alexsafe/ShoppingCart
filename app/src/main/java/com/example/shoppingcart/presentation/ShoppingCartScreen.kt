package com.example.shoppingcart.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppingcart.domain.CartLineCalculation
import java.math.BigDecimal
import java.text.NumberFormat

@Composable
fun ShoppingCartRoute(viewModel: ShoppingCartViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    ShoppingCartScreen(uiState)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ShoppingCartScreen(uiState: ShoppingCartUiState) {
    Scaffold(topBar = { TopAppBar(title = { Text("Shopping Cart") }) }) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(uiState.lineItems) { line -> CartLineCard(line) }
            item { CartSummary(uiState) }
        }
    }
}

@Composable
private fun CartLineCard(line: CartLineCalculation) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(line.item.name, style = MaterialTheme.typography.titleMedium)
            Text(
                "${line.item.category.displayName()} · Quantity: ${line.item.quantity}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.padding(top = 4.dp))
            AmountRow("Unit price", money(line.item.unitPrice))
            AmountRow("Item total (before discount)", money(line.preDiscountAmount))
            AmountRow("${line.item.category.displayName()} discount", "-${money(line.discount)}")
        }
    }
}

@Composable
private fun CartSummary(uiState: ShoppingCartUiState) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        HorizontalDivider()
        Spacer(Modifier.padding(top = 8.dp))
        AmountRow("Discounted subtotal", money(uiState.discountedSubtotal))
        AmountRow("Sales tax (8.5%)", money(uiState.salesTax))
        AmountRow("Total", money(uiState.total), isEmphasized = true)
    }
}

@Composable
private fun AmountRow(label: String, amount: String, isEmphasized: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        val textStyle = if (isEmphasized) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium
        Text(label, style = textStyle)
        Text(amount, style = textStyle)
    }
}

private fun money(amount: BigDecimal): String = NumberFormat.getCurrencyInstance().format(amount)

private fun com.example.shoppingcart.domain.Category.displayName(): String =
    name.lowercase().replaceFirstChar { it.uppercase() }
