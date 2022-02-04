package com.example.mechelin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.R
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

    }

    override fun getItemCount(): Int = reviewList.size

    inner class ViewHolder(val binding: ItemRecentReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(review: Review){
            binding.itemReviewResTitleTv.text = review.storeName
            binding.itemReviewDetailTv.text = review.contents
            binding.itemReviewDateTv.text = review.createdAt

            when(review.starRate){
                5.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_5)
                4.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_four_and_a_half)
                4.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_4)
                3.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_three_and_a_half)
                3.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_3)
                2.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_two_anf_a_half)
                2.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_2)
                1.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_one1and_a_half)
                1.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_1)
                0.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_half)
            }
        }

    }
}