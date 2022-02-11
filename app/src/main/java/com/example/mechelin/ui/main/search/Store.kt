package com.example.mechelin.ui.main.search

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("storeIdx") val storeIdx: Int = 0,
    @SerializedName("storeName") val storeName: String = "",
    @SerializedName("address") val address: String = ""
)
