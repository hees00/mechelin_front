package com.example.mechelin.ui.detail

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.mechelin.R
import com.example.mechelin.data.SearchStore
import com.example.mechelin.databinding.ItemPostBinding

class PostRVAdapter(private val postList: ArrayList<Review>,private val itemClickedListener:(Int)->Unit) : RecyclerView.Adapter<PostRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("post","item count : " + itemCount)
        Log.d("post","post Result : " + postList)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postResult: Review){
            binding.itemPostDate.text = postResult.createdDate
            binding.itemPostTxtPost.text = postResult.contents
            binding.itemPostHashTag.text = postResult.hashTags[0]

            binding.itemPostImgSlide.adapter = SlidePagerAdapter(postResult.imageUrl)
            binding.itemPostImgSlide.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            /* 여백, 너비에 대한 정의 */
            val pageMarginPx = 50
            val pagerWidth = 740
            val screenWidth = 1080
            val offsetPx = screenWidth - pageMarginPx - pagerWidth

            binding.itemPostImgSlide.setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }

            binding.itemPostImgSlide.offscreenPageLimit = 1 // 몇 개의 페이지를 미리 로드 해둘것인지

            when(postResult.starRate){
                5.0 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_5_xxhdpi)
                4.5 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_4andhalf_xxhdpi)
                4.0 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_4_xxhdpi)
                3.5 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_3andhalf_xxhdpi)
                3.0 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_3_xxhdpi)
                2.5 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_2andhalf_xxhdpi)
                2.0 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_2_xxhdpi)
                1.5 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_1andhalf_xxhdpi)
                1.0 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_1_xxhdpi)
                0.5 -> binding.itemPostRate.setImageResource(R.drawable.detailpage_writingpage_rating_half_xxhdpi)
            }

            binding.itemPostBtnDelete.setOnClickListener {
                itemClickedListener(postResult.reviewIdx)
            }

        }
    }


}