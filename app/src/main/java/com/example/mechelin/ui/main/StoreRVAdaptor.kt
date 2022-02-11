package com.example.mechelin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.R
import com.example.mechelin.databinding.ItemFoodBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreRVAdaptor(private var Foodlist: ArrayList<StoreResult>): RecyclerView.Adapter<StoreRVAdaptor.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFoodBinding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Foodlist[position])
    }

    override fun getItemCount(): Int = Foodlist.size

    inner class ViewHolder(val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(storeresult: StoreResult){
            binding.itemFoodAddressTv.text = storeresult.address
            binding.itemFoodStorenameTv.text = storeresult.storeName
            binding.listFoodHashtagTv.text = storeresult.tag.toString().replace("[","").replace("]","")

            CoroutineScope(Dispatchers.Main).launch {
                val bitmap = withContext(Dispatchers.IO){
                    ImageLoader.loadimage(storeresult.imageUrl)
//                    ImageLoader.loadimage("https://d20aeo683mqd6t.cloudfront.net/ko/articles/title_images/000/039/143/medium/IMG_5649%E3%81%AE%E3%82%B3%E3%83%92%E3%82%9A%E3%83%BC.jpg?2019")
                }
                if (bitmap == null){
                    binding.itemFoodPhoto03Iv.setImageResource(R.drawable.logo_main_01)
                }
                else{
                    binding.itemFoodPhoto03Iv.setImageBitmap(bitmap)
                }
            }

            when(storeresult.starRate){
                5.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_5)
                4.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_four_and_a_half)
                4.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_4)
                3.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_three_and_a_half)
                3.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_3)
                2.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_two_anf_a_half)
                2.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_2)
                1.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_one1and_a_half)
                1.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_1)
                0.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.home_rating_half)
            }
        }

    }
}