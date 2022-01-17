package com.angelawang.demo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.angelawang.demo.data.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(airStatusList: List<CurrencyInfo>)

    @Query("SELECT * FROM `currency_info_table`")
    fun getAll(): Flow<List<CurrencyInfo>>

}