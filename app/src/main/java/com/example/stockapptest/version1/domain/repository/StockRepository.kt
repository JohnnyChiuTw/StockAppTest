package com.example.stockapptest.version1.domain.repository

import com.example.stockapptest.version1.data.remote.dto.BwibbuDto
import com.example.stockapptest.version1.data.remote.dto.StockDayAvgDto
import com.example.stockapptest.version1.data.remote.dto.StockDayDto


interface StockRepository {

    suspend fun getBwibbus(): List<BwibbuDto>

    suspend fun getStockDays(): List<StockDayDto>

    suspend fun getStockDayAvgs(): List<StockDayAvgDto>
}