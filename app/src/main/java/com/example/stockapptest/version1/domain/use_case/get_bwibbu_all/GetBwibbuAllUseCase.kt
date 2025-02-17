package com.example.stockapptest.version1.domain.use_case.get_bwibbu_all


import com.example.stockapptest.version1.common.Resources
import com.example.stockapptest.version1.data.remote.dto.toBwibbu

import com.example.stockapptest.version1.domain.model.Bwibbu
import com.example.stockapptest.version1.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBwibbuAllUseCase @Inject constructor(
    private val repository: StockRepository
){
    operator fun invoke(): Flow<Resources<List<Bwibbu>>> = flow {
        try {
            emit(Resources.Loading<List<Bwibbu>>())
            val bwubbis = repository.getBwibbus().map { it.toBwibbu() }
            emit(Resources.Success<List<Bwibbu>>(bwubbis))
        } catch(e: HttpException){
            emit(Resources.Error<List<Bwibbu>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException){
            emit(Resources.Error<List<Bwibbu>>("Couldn't reach server, Check your internet connection"))
        }

    }
}