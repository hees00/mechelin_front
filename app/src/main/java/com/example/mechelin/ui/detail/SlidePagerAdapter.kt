package com.example.mechelin.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.R
import com.example.mechelin.databinding.ItemPostPagerImgBinding
import com.example.mechelin.ui.main.ImageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SlidePagerAdapter(val imageList : ArrayList<String>) : RecyclerView.Adapter<SlidePagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PagerViewHolder {
        val binding: ItemPostPagerImgBinding = ItemPostPagerImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO){
                ImageLoader.loadimage(imageList[position])
            }
            if (bitmap != null){
                holder.imgPost.setImageBitmap(bitmap)
            }else{
                holder.imgPost.setImageResource(R.drawable.logo_login_home_xxhdpi)
            }
        }
    }

    override fun getItemCount(): Int = imageList.size

    inner class PagerViewHolder(val binding: ItemPostPagerImgBinding): RecyclerView.ViewHolder(binding.root){
        val imgPost = binding.postImg
    }
}