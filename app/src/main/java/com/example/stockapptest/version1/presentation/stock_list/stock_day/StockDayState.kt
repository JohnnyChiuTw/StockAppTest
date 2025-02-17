package com.example.stockapptest.version1.presentation.stock_list.stock_day

import com.example.stockapptest.version1.domain.model.StockDay

data class StockDayState(
    val isLoading: Boolean = false,
    var stockDays: List<StockDay> = emptyList(),
    val error: String = ""
)
