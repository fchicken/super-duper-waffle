package com.angelawang.demo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_info_table")
data class CurrencyInfo(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String
)