package com.example.mechelin.ui.main

import com.google.gson.annotations.SerializedName

data class StoreResult(
    @SerializedName("storeIdx") val storeIdx: Int = 0,
    @SerializedName("imageUrl") val imageUrl: String = "",
    @SerializedName("storeName") val storeName: String = "",
    @SerializedName("starRate") val starRate: Double = 0.0,
    @SerializedName("tag") val tag: ArrayList<String>,
    @SerializedName("address") val address: String = "",
    @SerializedName("contents") val contents: String = "",
    @SerializedName("createdAt") val createdAt: String = ""
)
