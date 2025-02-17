package com.example.stockapptest.version1.presentation.stock_list.beibbu

import com.example.stockapptest.version1.domain.model.Bwibbu


data class BwibbuState(
    val isLoading: Boolean = false,
    val bwibbus: List<Bwibbu> = emptyList(),
    val error: String = ""
)
