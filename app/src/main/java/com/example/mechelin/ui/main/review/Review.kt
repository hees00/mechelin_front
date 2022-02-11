package com.example.mechelin.ui.main.review

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("userIdx") val userIdx: Int = 0,
    @SerializedName("storeIdx") val storeIdx: Int = 0,
    @SerializedName("storeName") val storeName: String = "",
    @SerializedName("starRate") val starRate: Double = 0.0,
    @SerializedName("contents") val contents: String = "",
    @SerializedName("createdAt") val createdAt: String = ""
)
