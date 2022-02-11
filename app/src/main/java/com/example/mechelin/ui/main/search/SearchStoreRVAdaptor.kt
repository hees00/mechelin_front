package com.example.mechelin.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.databinding.ItemStoreSearchingBinding

class SearchStoreRVAdaptor(private var storeList: ArrayList<Store>): RecyclerView.Adapter<SearchStoreRVAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStoreSearchingBinding = ItemStoreSearchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(storeList[position])
    }

    override fun getItemCount(): Int = storeList.size

    inner class ViewHolder(val binding: ItemStoreSearchingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(store: Store){
            binding.itemStoreSearchingStorenameTv.text = store.storeName
            binding.itemStoreSearchingStoreaddressTv.text = store.address
        }
    }
}
