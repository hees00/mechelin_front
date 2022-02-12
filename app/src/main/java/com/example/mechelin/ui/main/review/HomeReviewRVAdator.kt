package com.example.mechelin.ui.main.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.R
import com.example.mechelin.databinding.ItemRecentReviewBinding

class HomeReviewRVAdator(private var reviewList: ArrayList<Review>): RecyclerView.Adapter<HomeReviewRVAdator.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemRecentReviewBinding = ItemRecentReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviewList[position])

    }

    override fun getItemCount(): Int = reviewList.size

    inner class ViewHolder(val binding: ItemRecentReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(review: Review){
            binding.itemReviewResTitleTv.text = review.storeName
            binding.itemReviewDetailTv.text = review.contents
            binding.itemReviewDateTv.text = review.createdAt

            when(review.starRate){
                5.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_5_xxhdpi)
                4.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_4andhalf_xxhdpi)
                4.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_4_xxhdpi)
                3.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_3andhalf_xxhdpi)
                3.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_3_xxhdpi)
                2.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_2andhalf_xxhdpi)
                2.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_2_xxhdpi)
                1.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_1andhalf_xxhdpi)
                1.0 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_1_xxhdpi)
                0.5 -> binding.itemReviewScoreIv.setImageResource(R.drawable.home_rating_half_xxhdpi)
            }
        }

    }
}