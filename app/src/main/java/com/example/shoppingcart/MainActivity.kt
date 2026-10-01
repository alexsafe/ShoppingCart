package com.example.shoppingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.shoppingcart.data.InMemoryCartRepository
import com.example.shoppingcart.domain.CartCalculator
import com.example.shoppingcart.presentation.ShoppingCartRoute
import com.example.shoppingcart.presentation.ShoppingCartViewModel
import com.example.shoppingcart.presentation.ShoppingCartViewModelFactory
import com.example.shoppingcart.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ShoppingCartViewModel by viewModels {
        ShoppingCartViewModelFactory(InMemoryCartRepository(), CartCalculator())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                ShoppingCartRoute(viewModel)
            }
        }
    }
}
