package com.angelawang.demo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelawang.demo.data.model.CurrencyInfo
import com.angelawang.demo.databinding.ItemCurrencyInfoBinding


class CurrencyInfoAdapter: RecyclerView.Adapter<CurrencyInfoAdapter.CurrencyInfoViewHolder>() {

    var infoList = listOf<CurrencyInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyInfoAdapter.CurrencyInfoViewHolder {
        val binding = ItemCurrencyInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyInfoAdapter.CurrencyInfoViewHolder, position: Int) {
        holder.bind(infoList[position])
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    fun setList(list: List<CurrencyInfo>) {
        infoList = list
        notifyDataSetChanged()
    }

    inner class CurrencyInfoViewHolder(private val binding: ItemCurrencyInfoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(info: CurrencyInfo) {
            binding.iconTextView.text = info.name[0].uppercase()
            binding.nameTextView.text = info.name
            binding.symbolTextView.text = info.symbol
        }
    }

}