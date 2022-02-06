package com.example.mechelin.data.remote

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("userIdx") var userIdx:Int,
    @SerializedName("categoryIdx")var categoryIdx:Int,
    @SerializedName("deliveryService")var deliveryService: String,
    @SerializedName("storeName")var storeName:String,
    @SerializedName("address") var address: String,
    @SerializedName("x")var x: Double,
    @SerializedName("y")var y: Double,
    @SerializedName("tel")var tel: String,
    @SerializedName("tagName")var tagName: ArrayList<String>,
    @SerializedName("starRate")var starRate: Double,
    @SerializedName("contents")var contents: String
)

data class Review(
    var starRate: Double,
    var contents: String,
    var tagName: List<String>
)