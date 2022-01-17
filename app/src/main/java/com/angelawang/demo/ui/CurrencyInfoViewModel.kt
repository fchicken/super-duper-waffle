package com.angelawang.demo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.angelawang.demo.data.CurrencyInfoRepository
import com.angelawang.demo.data.model.CurrencyInfo

class CurrencyInfoViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CurrencyInfoRepository(application)

    private val currencyInfoList: LiveData<List<CurrencyInfo>> = repository.getList().asLiveData()

    fun getList(): LiveData<List<CurrencyInfo>> {
        return currencyInfoList
    }

}