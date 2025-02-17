package com.example.stockapptest.version1.data.remote.dto

import com.example.stockapptest.version1.domain.model.StockDayAvg

data class StockDayAvgDto(
    val ClosingPrice: String,
    val Code: String,
    val MonthlyAveragePrice: String,
    val Name: String
)

fun StockDayAvgDto.toStockDayAvg(): StockDayAvg{
    return StockDayAvg(
        ClosingPrice = ClosingPrice,
        Code = Code,
        MonthlyAveragePrice = MonthlyAveragePrice,
        Name = Name

    )
}