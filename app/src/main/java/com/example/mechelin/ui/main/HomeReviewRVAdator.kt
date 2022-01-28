package com.example.mechelin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.databinding.ItemRecentReviewBinding

class HomeReviewRVAdator(private var reviewList: ArrayList<Review>): RecyclerView.Adapter<HomeReviewRVAdator.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeReviewRVAdator.ViewHolder {
        val binding: ItemRecentReviewBinding = ItemRecentReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeReviewRVAdator.ViewHolder, position: Int) {
        holder.bind(reviewList[position])

//        holder.itemView.setOnClickListener{}
    }

    override fun getItemCount(): Int = reviewList.size

    inner class ViewHolder(val binding: ItemRecentReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(review: Review){
            binding.itemReviewResTitleTv.text = review.storeName
            binding.itemReviewDetailTv.text = review.contents
            binding.itemReviewDateTv.text = review.createdAt
//            binding.itemReviewScoreIv.setImageResource()
        }

    }
}