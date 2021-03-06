package com.example.mechelin.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.R
import com.example.mechelin.databinding.ItemFoodBinding
import com.example.mechelin.ui.detail.DetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HashtagStoreRVAdaptor(private var Foodlist: ArrayList<HashtagStoreResult>): RecyclerView.Adapter<HashtagStoreRVAdaptor.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFoodBinding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Foodlist[position])
    }

    override fun getItemCount(): Int = Foodlist.size

    inner class ViewHolder(val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(Hashtagstoreresult: HashtagStoreResult){
            binding.itemFoodAddressTv.text = Hashtagstoreresult.address
            binding.itemFoodStorenameTv.text = Hashtagstoreresult.storeName
            binding.listFoodHashtagTv.text = Hashtagstoreresult.tag.toString().replace("[","").replace("]","")

            CoroutineScope(Dispatchers.Main).launch {
                val bitmap = withContext(Dispatchers.IO){
                    ImageLoader.loadimage(Hashtagstoreresult.imageUrl)
//                    ImageLoader.loadimage("https://d20aeo683mqd6t.cloudfront.net/ko/articles/title_images/000/039/143/medium/IMG_5649%E3%81%AE%E3%82%B3%E3%83%92%E3%82%9A%E3%83%BC.jpg?2019")
                }
                if (bitmap == null){
                    binding.itemFoodPhoto03Iv.setImageResource(R.drawable.logo_login_home_xxhdpi)
                }
                else{
                    binding.itemFoodPhoto03Iv.setImageBitmap(bitmap)
                }
            }

            when(Hashtagstoreresult.starRate){
                5.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_5_xxhdpi)
                4.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_4andhalf_xxhdpi)
                4.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_4_xxhdpi)
                3.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_3andhalf_xxhdpi)
                3.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_3_xxhdpi)
                2.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_2andhalf_xxhdpi)
                2.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_2_xxhdpi)
                1.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_1andhalf_xxhdpi)
                1.0 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_1_xxhdpi)
                0.5 -> binding.itemFoodStarrate03Iv.setImageResource(R.drawable.listpage_rating_half_xxhdpi)
            }

            //list Fragment -> Detail Activity ????????????
            val storeIdx = Hashtagstoreresult.storeIdx

            binding.itemFoodPhoto03Iv.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    val intent = Intent(v.context, DetailActivity::class.java)
                    intent.putExtra("storeIdx",storeIdx)
                    v.context.startActivity(intent)
                }
            })
        }

    }
}