package com.angelawang.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.angelawang.demo.data.CurrencyInfoRepository
import com.angelawang.demo.data.model.CurrencyInfo
import com.angelawang.demo.databinding.ActivityDemoBinding
import com.angelawang.demo.extensions.setOnClickListener
import com.angelawang.demo.ui.CurrencyInfoViewModel

class DemoActivity : AppCompatActivity() {

    private var binding: ActivityDemoBinding? = null
    private val currencyInfoViewModel: CurrencyInfoViewModel by viewModels()

    private var currentSortType = CurrencyInfoRepository.SortType.NONE
    private var toastMessage: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBinding = ActivityDemoBinding.inflate(layoutInflater).apply {
            binding = this
        }
        setContentView(activityBinding.root)

        activityBinding.loadButton.setOnClickListener {
            activityBinding.apply {
                loadButton.isEnabled = false
                sortButton.isEnabled = true
                loadingProgressBar.visibility = View.VISIBLE
            }
            currencyInfoViewModel.setSortType(CurrencyInfoRepository.SortType.DEFAULT)
        }

        activityBinding.sortButton.setOnClickListener(300) {
            currencyInfoViewModel.setSortType(CurrencyInfoRepository.SortType.next(currentSortType))
        }

        // Set button state according to list is empty or not
        currencyInfoViewModel.getList().observe(this, object: Observer<List<CurrencyInfo>> {
            override fun onChanged(list: List<CurrencyInfo>?) {
                if (list == null || list.isEmpty()) {
                    activityBinding.apply {
                        loadButton.isEnabled = true
                        sortButton.isEnabled = false
                    }
                } else {
                    activityBinding.apply {
                        loadButton.isEnabled = false
                        sortButton.isEnabled = true
                        loadingProgressBar.visibility = View.GONE
                    }
                    // No need to observe list anymore after it's loaded
                    currencyInfoViewModel.getList().removeObserver(this)
                }
            }
        })

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

        // Get notification when info item is clicked
        currencyInfoViewModel.getClickedInfo().observe(this, { info ->
            // Cancel previous message first in order to show current message immediately
            toastMessage?.cancel()
            toastMessage = Toast.makeText(this, "${info.symbol} is Clicked", Toast.LENGTH_SHORT)
            toastMessage?.show()
        })
    }

    override fun onStop() {
        super.onStop()
        toastMessage?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}