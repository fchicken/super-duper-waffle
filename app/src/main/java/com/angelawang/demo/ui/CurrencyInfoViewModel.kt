package com.angelawang.demo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.angelawang.demo.classes.SingleLiveEvent
import com.angelawang.demo.data.CurrencyInfoRepository
import com.angelawang.demo.data.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class CurrencyInfoViewModel(application: Application): AndroidViewModel(application) {

    private val repository = CurrencyInfoRepository(application)

    private val sortType: MutableStateFlow<CurrencyInfoRepository.SortType> = MutableStateFlow(CurrencyInfoRepository.SortType.NONE)
    private val currencyInfoList: Flow<List<CurrencyInfo>> = sortType.flatMapLatest { sortType -> repository.getList(sortType) }

    // Use SingleLiveEvent to prevent trigger again upon configuration change/screen rotation
    private val clickedInfo: SingleLiveEvent<CurrencyInfo> = SingleLiveEvent()

    fun setSortType(type: CurrencyInfoRepository.SortType) {
        sortType.value = type
    }
    fun getSortType(): LiveData<CurrencyInfoRepository.SortType> {
        return sortType.asLiveData()
    }

    fun getList(): LiveData<List<CurrencyInfo>> {
        return currencyInfoList.asLiveData()
    }

    fun setClickedInfo(info: CurrencyInfo) {
        clickedInfo.value = info
    }
    fun getClickedInfo(): LiveData<CurrencyInfo> {
        return clickedInfo
    }

}