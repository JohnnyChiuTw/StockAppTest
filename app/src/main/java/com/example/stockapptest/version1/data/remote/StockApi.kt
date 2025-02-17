package com.example.stockapptest.version1.data.remote


import com.example.stockapptest.version1.common.Resources
import com.example.stockapptest.version1.data.remote.dto.BwibbuDto
import com.example.stockapptest.version1.data.remote.dto.StockDayAvgDto
import com.example.stockapptest.version1.data.remote.dto.StockDayDto
import retrofit2.Response
import retrofit2.http.GET

interface StockApi {


    @GET("exchangeReport/STOCK_DAY_ALL")
    suspend fun getStockDayAll(): List<StockDayDto>

    @GET("exchangeReport/STOCK_DAY_AVG_ALL")
    suspend fun getStockDayAvgAll(): List<StockDayAvgDto>

    @GET("exchangeReport/BWIBBU_ALL")
    suspend fun getBwibbuAll(): List<BwibbuDto>

   
}