package com.example.mechelin.ui.main

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("storeIdx") val storeIdx: Int = 0,
    @SerializedName("imageUrl") val imageUrl: String = "",
    @SerializedName("storeName") val storeName: String = "",
    @SerializedName("starRate") val starRate: Float = 0F,
    @SerializedName("tag") val tag: ArrayList<String>,
    @SerializedName("address") val address: String = "",
    @SerializedName("contents") val contents: String = "",
    @SerializedName("createdAt") val createdAt: String = ""
)
