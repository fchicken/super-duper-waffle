package com.angelawang.demo.data

import android.app.Application
import com.angelawang.demo.data.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

class CurrencyInfoRepository(application: Application) {

    private val currencyInfoDao = CurrencyInfoDatabase.getInstance(application).currencyInfoDao()

    fun getList(): Flow<List<CurrencyInfo>> {
        return currencyInfoDao.getAll()
    }
}