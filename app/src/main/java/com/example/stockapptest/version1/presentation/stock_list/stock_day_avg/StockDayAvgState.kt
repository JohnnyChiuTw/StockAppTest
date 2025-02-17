package com.example.stockapptest.version1.presentation.stock_list.stock_day_avg

import com.example.stockapptest.version1.domain.model.StockDayAvg

data class StockDayAvgState(
    val isLoading: Boolean = false,
    val stockDayAvgs: List<StockDayAvg> = emptyList(),
    val error: String = ""
)
