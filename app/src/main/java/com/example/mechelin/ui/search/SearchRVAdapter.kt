package com.example.mechelin.ui.save

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.R
import com.example.mechelin.data.SearchStore
import com.example.mechelin.databinding.ItemSearchResultBinding


class SearchRVAdapter(private val StoreList: ArrayList<SearchStore>,private val itemClickedListener:(SearchStore)->Unit) : RecyclerView.Adapter<SearchRVAdapter.ViewHolder>() {
    //뷰홀더를 생성해줘야 할 때 생성되는 함수
    //private val itemClickedListener:(SearchStore)->Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRVAdapter.ViewHolder {
        val binding: ItemSearchResultBinding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    //생서된 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: SearchRVAdapter.ViewHolder, position: Int) {
        holder.bind(StoreList[position])
    }

    override fun getItemCount(): Int = StoreList.size

    inner class ViewHolder(val binding: ItemSearchResultBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(store: SearchStore){
            binding.itemSearchItemStorenameTv.text=store.storename
            binding.itemSearchItemStoreaddressTv.text=store.storeaddress
            binding.itemSearchPhoneTv.text=store.phone
            binding.itemSearchXTv.text=store.x
            binding.itemSearchYTv.text=store.y

            itemView.setOnClickListener {
                itemClickedListener(store)
            }

        }



    }


}