package com.example.stockapptest.version1.data.remote.dto

import com.example.stockapptest.version1.domain.model.Bwibbu

data class BwibbuDto(
    val Code: String,
    val DividendYield: String,
    val Name: String,
    val PBratio: String,
    val PEratio: String
)

fun BwibbuDto.toBwibbu(): Bwibbu {
    return Bwibbu(
        Code = Code,
        DividendYield = DividendYield,
        Name = Name,
        PBratio = PBratio,
        PEratio = PEratio
    )
}