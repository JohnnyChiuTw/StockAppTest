package com.example.stockapptest.version1.domain.use_case.get_stock_day_avg_all

import com.example.stockapptest.version1.common.Resources
import com.example.stockapptest.version1.data.remote.dto.toStockDay
import com.example.stockapptest.version1.data.remote.dto.toStockDayAvg
import com.example.stockapptest.version1.domain.model.StockDay
import com.example.stockapptest.version1.domain.model.StockDayAvg
import com.example.stockapptest.version1.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetStockDayAvgAllUseCase @Inject constructor(
    private val repository: StockRepository
) {

    operator fun invoke(): Flow<Resources<List<StockDayAvg>>> = flow {
        try {
            emit(Resources.Loading<List<StockDayAvg>>())
            val stockDayAvgs = repository.getStockDayAvgs().map { it.toStockDayAvg() }
            emit(Resources.Success<List<StockDayAvg>>(stockDayAvgs))
        } catch(e: HttpException){
            emit(Resources.Error<List<StockDayAvg>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException){
            emit(Resources.Error<List<StockDayAvg>>("Couldn't reach server, Check your internet connection"))
        }

    }

}