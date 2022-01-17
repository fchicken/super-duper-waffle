package com.angelawang.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.angelawang.demo.data.model.CurrencyInfo
import com.angelawang.demo.databinding.FragmentCurrencyListBinding

class CurrencyListFragment: Fragment() {

    private var binding: FragmentCurrencyListBinding? = null
    private var adapter: CurrencyInfoAdapter? = null

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

        adapter = CurrencyInfoAdapter().apply {
            val testList = listOf(
                CurrencyInfo("BTC", "Bitcoin", "BTC"),
                CurrencyInfo("ETH", "Ethereum", "ETH"),
                CurrencyInfo("XRP", "XRP", "XRP"),
                CurrencyInfo("BCH", "Bitcoin Cash", "BCH"),
                CurrencyInfo("LTC", "Litecoin", "LTC"),
                CurrencyInfo("EOS", "EOS", "EOS"),
                CurrencyInfo("BNB", "Binance Coin", "BNB"),
                CurrencyInfo("NEO", "NEO", "NEO"),
                CurrencyInfo("ETC", "Ethereum Classic", "ETC"),
                CurrencyInfo("ONT", "Ontology", "ONT"),
                CurrencyInfo("CRO", "Crypto.com Chain", "CRO"),
                CurrencyInfo("CUC", "Cucumber", "CUC"),
                CurrencyInfo("USDC", "USD Coin", "USDC")
            )
            setList(testList)
        }
        binding?.recyclerView?.adapter = adapter
    }
}