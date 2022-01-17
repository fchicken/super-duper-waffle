package com.angelawang.demo.data

import android.app.Application
import com.angelawang.demo.data.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CurrencyInfoRepository(application: Application) {

    enum class SortType {
        NONE, DEFAULT, ASCENDING, DESCENDING;

        companion object {
            fun next(sortType: SortType): SortType {
                var nextSortTypeIndex = (sortType.ordinal + 1) % values().size
                if (nextSortTypeIndex == 0) {
                    nextSortTypeIndex = 1
                }
                return values()[nextSortTypeIndex]
            }
        }
    }
    private val currencyInfoDao = CurrencyInfoDatabase.getInstance(application).currencyInfoDao()

    fun getList(sortType: SortType): Flow<List<CurrencyInfo>> {
        return when(sortType) {
            SortType.DEFAULT -> currencyInfoDao.getAll()
            SortType.ASCENDING -> currencyInfoDao.getAllAscending()
            SortType.DESCENDING -> currencyInfoDao.getAllDescending()
            else -> flowOf(listOf())
        }
    }
}