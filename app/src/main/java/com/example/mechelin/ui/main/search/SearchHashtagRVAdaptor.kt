package com.example.mechelin.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.databinding.ItemHashtagSearchingBinding

class SearchHashtagRVAdaptor(private var hashtagList: ArrayList<Hashtag>): RecyclerView.Adapter<SearchHashtagRVAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHashtagSearchingBinding = ItemHashtagSearchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hashtagList[position])
    }

    override fun getItemCount(): Int = hashtagList.size

    inner class ViewHolder(val binding: ItemHashtagSearchingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(hashtag: Hashtag){
            binding.itemHashtagSearchingNameTv.text = hashtag.tagName
            binding.itemHashtagSearchingReviewnumTv.text = hashtag.count.toString()
        }
    }
}