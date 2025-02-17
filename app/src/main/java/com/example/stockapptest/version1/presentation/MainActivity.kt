package com.example.stockapptest.version1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.stockapptest.ui.theme.StockAppTestTheme
import com.example.stockapptest.version1.presentation.stock_list.StockListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            StockAppTestTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    StockListScreen()
                }
            }
        }
    }
}
