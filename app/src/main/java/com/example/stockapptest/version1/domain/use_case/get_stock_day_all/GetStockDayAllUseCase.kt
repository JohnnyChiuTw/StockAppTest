package com.example.stockapptest.version1.domain.use_case.get_stock_day_all

import com.example.stockapptest.version1.common.Resources
import com.example.stockapptest.version1.data.remote.dto.toStockDay

import com.example.stockapptest.version1.domain.model.StockDay
import com.example.stockapptest.version1.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStockDayAllUseCase @Inject constructor(
    private val repository: StockRepository
) {

    operator fun invoke(): Flow<Resources<List<StockDay>>> = flow {
        try {
            emit(Resources.Loading<List<StockDay>>())
            val stockDays = repository.getStockDays().map { it.toStockDay() }
            emit(Resources.Success<List<StockDay>>(stockDays))
        } catch(e: HttpException){
            emit(Resources.Error<List<StockDay>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException){
            emit(Resources.Error<List<StockDay>>("Couldn't reach server, Check your internet connection"))
        }

    }

}