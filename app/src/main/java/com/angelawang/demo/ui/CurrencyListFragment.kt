package com.angelawang.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.angelawang.demo.data.model.CurrencyInfo
import com.angelawang.demo.databinding.FragmentCurrencyListBinding

class CurrencyListFragment: Fragment() {

    private var binding: FragmentCurrencyListBinding? = null
    private var adapter: CurrencyInfoAdapter? = null
    private val currencyInfoViewModel: CurrencyInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            adapter = CurrencyInfoAdapter()
            adapter?.setOnItemClickListener(object: CurrencyInfoAdapter.OnItemClickListener {
                override fun onItemClick(info: CurrencyInfo) {
                    currencyInfoViewModel.setClickedInfo(info)
                }
            })
            recyclerView.adapter = adapter
        }

        // Update list in adapter when it changed
        currencyInfoViewModel.getList().observe(viewLifecycleOwner, {list -> adapter?.setList(list)})
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}