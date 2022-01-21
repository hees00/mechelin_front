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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(val binding: ItemRecentReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(review: Review){
            binding.itemReviewResTitleTv.text = review.title
            binding.itemReviewScoreIv.setImageResource(review.starscore!!)
            binding.itemReviewScoreTv.text = review.starscoreinfo
            binding.itemReviewDetailTv.text = review.detail
        }
    }
}