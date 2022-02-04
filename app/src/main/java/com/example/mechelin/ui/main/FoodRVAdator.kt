package com.example.mechelin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.databinding.ItemFoodBinding
import com.example.mechelin.databinding.ItemRecentReviewBinding

class FoodRVAdator(private var Foodlist: ArrayList<Food>): RecyclerView.Adapter<FoodRVAdator.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFoodBinding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Foodlist[position])
    }

    override fun getItemCount(): Int = Foodlist.size

    inner class ViewHolder(val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
//            binding.itemFoodPhotoIv.setImageResource(food.imageUrl)
            binding.itemFoodAddressTv.text = food.address
            binding.itemFoodStorenameTv.text = food.storeName
//            binding.itemFoodStarrateIv.setImageResource()
        }

    }
}