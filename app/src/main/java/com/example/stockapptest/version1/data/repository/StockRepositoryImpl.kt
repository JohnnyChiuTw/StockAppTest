package com.example.stockapptest.version1.data.repository

import com.example.stockapptest.version1.data.remote.StockApi
import com.example.stockapptest.version1.data.remote.dto.BwibbuDto
import com.example.stockapptest.version1.data.remote.dto.StockDayAvgDto
import com.example.stockapptest.version1.data.remote.dto.StockDayDto
import com.example.stockapptest.version1.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val api: StockApi
) : StockRepository{

    override suspend fun getBwibbus(): List<BwibbuDto> {
        return api.getBwibbuAll()
    }

    override suspend fun getStockDays(): List<StockDayDto> {
        return api.getStockDayAll()
    }

    override suspend fun getStockDayAvgs(): List<StockDayAvgDto> {
        return api.getStockDayAvgAll()
    }


}