package com.example.stockapptest.version1.presentation.stock_list.stock_day

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockapptest.version1.common.Resources
import com.example.stockapptest.version1.domain.model.Bwibbu
import com.example.stockapptest.version1.domain.model.StockDay
import com.example.stockapptest.version1.domain.model.StockDayAndAvgForUi
import com.example.stockapptest.version1.domain.model.StockDayAvg
import com.example.stockapptest.version1.domain.repository.StockRepository
import com.example.stockapptest.version1.domain.use_case.get_bwibbu_all.GetBwibbuAllUseCase
import com.example.stockapptest.version1.domain.use_case.get_stock_day_all.GetStockDayAllUseCase
import com.example.stockapptest.version1.domain.use_case.get_stock_day_avg_all.GetStockDayAvgAllUseCase
import com.example.stockapptest.version1.presentation.stock_list.beibbu.BwibbuState
import com.example.stockapptest.version1.presentation.stock_list.stock_day_avg.StockDayAvgState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class StockDayViewModel @Inject constructor(
    private val getStockDayAllUseCase: GetStockDayAllUseCase,
    private val getStockDayAvgAllUseCase: GetStockDayAvgAllUseCase,
    private val getBwibbuAllUseCase: GetBwibbuAllUseCase
): ViewModel() {


    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading


    //old
    private val _stateDay = mutableStateOf(StockDayState())
    val stateDay: State<StockDayState> = _stateDay

    private val _stateDayAvg = mutableStateOf(StockDayAvgState())
    val stateDayAvg: State<StockDayAvgState> = _stateDayAvg

    private val _statebwibbu = mutableStateOf(BwibbuState())
    val statebwibbu: State<BwibbuState> = _statebwibbu



    init {
        getStockDays()
        getBwibbu()
    }



    private fun getStockDays() {
        getStockDayAllUseCase().onEach { result ->
            when(result) {
                is Resources.Success -> {
                    _stateDay.value = StockDayState(stockDays = result.data ?: emptyList())
                    Log.d("Log2", _stateDay.value.stockDays.toString())
                    //myMutableList = _stateDay.value.stockDays.toMutableList()
                    getStockDayAvg()
                    if(_stateDayAvg.value.stockDayAvgs.isNotEmpty() &&
                        _stateDay.value.stockDays.isNotEmpty()) {
                        _isLoading.value = false
                    }

                }
                is Resources.Error -> {
                    _stateDay.value = StockDayState(error = result.message ?:
                    "An unexpected error occurred")

                }
                is Resources.Loading -> {
                    _stateDay.value = StockDayState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getStockDayAvg() {

        getStockDayAvgAllUseCase().onEach { result ->
            when(result) {
                is Resources.Success -> {
                    _stateDayAvg.value = StockDayAvgState(stockDayAvgs = result.data ?: emptyList())
                    Log.d("Log3", _stateDayAvg.value.stockDayAvgs.toString())
                    if(_stateDay.value.stockDays.isNotEmpty() &&
                        _stateDayAvg.value.stockDayAvgs.isNotEmpty()) {
                        _isLoading.value = false
                    }
                }
                is Resources.Error -> {
                    _stateDayAvg.value = StockDayAvgState(error = result.message ?:
                    "An unexpected error occurred")

                }
                is Resources.Loading -> {
                    _stateDayAvg.value = StockDayAvgState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBwibbu() {
        getBwibbuAllUseCase().onEach { result ->
            when(result) {
                is Resources.Success -> {
                    _statebwibbu.value = BwibbuState(bwibbus = result.data ?: emptyList())
                }
                is Resources.Error -> {
                    _stateDay.value = StockDayState(error = result.message ?:
                    "An unexpected error occurred")

                }
                is Resources.Loading -> {
                    _stateDay.value = StockDayState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }


}