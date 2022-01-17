package com.angelawang.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.angelawang.demo.data.CurrencyInfoRepository
import com.angelawang.demo.databinding.ActivityDemoBinding
import com.angelawang.demo.ui.CurrencyInfoViewModel

class DemoActivity : AppCompatActivity() {

    private var binding: ActivityDemoBinding? = null
    private val currencyInfoViewModel: CurrencyInfoViewModel by lazy {
        ViewModelProvider(this).get(CurrencyInfoViewModel::class.java)
    }
    private var currentSortType = CurrencyInfoRepository.SortType.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBinding = ActivityDemoBinding.inflate(layoutInflater).apply {
            binding = this
        }
        setContentView(activityBinding.root)

        activityBinding.loadButton.setOnClickListener {
            activityBinding.loadButton.isEnabled = false
            activityBinding.sortButton.isEnabled = true
            currencyInfoViewModel.setSortType(CurrencyInfoRepository.SortType.DEFAULT)
        }
        activityBinding.sortButton.setOnClickListener {
            currencyInfoViewModel.setSortType(CurrencyInfoRepository.SortType.next(currentSortType))
        }

        // Update current sort type and sort button text when it changed
        currencyInfoViewModel.getSortType().observe(this, { sortType ->
            currentSortType = sortType
            activityBinding.sortButton.text = when(currentSortType) {
                CurrencyInfoRepository.SortType.DEFAULT -> getString(R.string.activity_demo_sort_default)
                CurrencyInfoRepository.SortType.ASCENDING -> getString(R.string.activity_demo_sort_ascending)
                CurrencyInfoRepository.SortType.DESCENDING -> getString(R.string.activity_demo_sort_descending)
                else -> getString(R.string.activity_demo_sort)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}