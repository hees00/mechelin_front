package com.example.mechelin.data.remote

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("userIdx") var userIdx:Int,
    @SerializedName("storeName")var storeName:String,
    @SerializedName("address") var address: String,
    @SerializedName("tel")var tel: String,
    @SerializedName("x")var x: Double,
    @SerializedName("y")var y: Double,
    @SerializedName("starRate")var starRate: Double,
    @SerializedName("contents")var contents: String,
    @SerializedName("tagName")var tagName: ArrayList<String>,
    @SerializedName("categoryIdx")var categoryIdx:Int,
    @SerializedName("deliveryService")var deliveryService: String
)

data class Review(
    var starRate: Double,
    var contents: String,
    var tagName: List<String>
)