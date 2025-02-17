package com.example.stockapptest.version1.data.remote.dto

import com.example.stockapptest.version1.domain.model.StockDay


data class StockDayDto(
    val Change: String,
    val ClosingPrice: String,
    val Code: String,
    val HighestPrice: String,
    val LowestPrice: String,
    val Name: String,
    val OpeningPrice: String,
    val TradeValue: String,
    val TradeVolume: String,
    val Transaction: String
)

fun StockDayDto.toStockDay() : StockDay {
    return StockDay(
        Change = Change,
        ClosingPrice = ClosingPrice,
        Code = Code,
        HighestPrice = HighestPrice,
        LowestPrice = LowestPrice,
        Name = Name,
        OpeningPrice = OpeningPrice,
        TradeValue = TradeValue,
        TradeVolume = TradeVolume,
        Transaction = Transaction
    )
}
