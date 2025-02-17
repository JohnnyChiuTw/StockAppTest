package com.example.stockapptest.version1.presentation.stock_list.beibbu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockapptest.version1.common.Resources
import com.example.stockapptest.version1.domain.use_case.get_bwibbu_all.GetBwibbuAllUseCase
import com.example.stockapptest.version1.domain.use_case.get_stock_day_all.GetStockDayAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class BwibbuViewModel @Inject constructor(
    private val getBwibbuAllUseCase: GetBwibbuAllUseCase
): ViewModel() {

    private val _state = mutableStateOf(BwibbuState())
    val state: State<BwibbuState> = _state


    init {
        getBwibbu()
    }

    private fun getBwibbu() {
        getBwibbuAllUseCase().onEach { result ->
            when(result) {
                is Resources.Success -> {
                    _state.value = BwibbuState(bwibbus = result.data ?: emptyList())
                }
                is Resources.Error -> {
                    _state.value = BwibbuState(error = result.message ?:
                    "An unexpected error occurred")
                }
                is Resources.Loading -> {
                    _state.value = BwibbuState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}