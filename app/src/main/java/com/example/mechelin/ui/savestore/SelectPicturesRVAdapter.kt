package com.example.mechelin.ui.savestore

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechelin.databinding.ItemSearchResultBinding
import com.example.mechelin.databinding.ItemUploadPictureBinding
import java.io.File
import java.net.URI

class SelectPicturesRVAdapter(val picturelist: ArrayList<Uri>):RecyclerView.Adapter<SelectPicturesRVAdapter.ViewHolder>() {
    val imageList = arrayListOf<Drawable>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectPicturesRVAdapter.ViewHolder {
        val binding: ItemUploadPictureBinding =
            ItemUploadPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectPicturesRVAdapter.ViewHolder, position: Int) {
        holder.bind(picturelist[position])
    }

    override fun getItemCount(): Int = picturelist.size

    inner class ViewHolder(val binding: ItemUploadPictureBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(uri: Uri) {
            binding.writingUploadIv.setImageURI((uri))
            val image= binding.writingUploadIv.drawable
            imageList.add(image)
        }

    }

}