package com.example.mechelin.ui.main.search

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.databinding.ItemHashtagSearchingBinding
import com.example.mechelin.ui.main.SearchwordHashtagActivity
import com.example.mechelin.ui.main.getTagIdx
import com.example.mechelin.ui.main.saveTagIdx

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

            val tagIdx = hashtag.tagIdx

            binding.itemHashtagSearchingNameTv.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    val intent = Intent(v.context, SearchwordHashtagActivity::class.java)
                    saveTagIdx(v.context, tagIdx)
                    Log.d("VIEWTAGIDX", tagIdx.toString())
                    intent.putExtra("tagIdx",tagIdx)
                    v.context.startActivity(intent)
                }
            })
        }
    }
}