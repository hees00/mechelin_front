package com.example.mechelin.ui.detail

import com.google.gson.annotations.SerializedName

data class PostResult(
    @SerializedName("result") val result: ReviewResult,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class ReviewResult(
    @SerializedName("storeInformation") val storeInformation: StoreInformation,
    @SerializedName("reviewList") val reviewList: ArrayList<Review>,
)

data class StoreInformation(
    @SerializedName("averagestarRate") val averageStarRate: Double,
    @SerializedName("storeName") val storeName: String,
    @SerializedName("address") val storeAddress: String,
    @SerializedName("tel") val storeTel: String
)

data class Review(
    @SerializedName("reviewIdx") val reviewIdx: Int,
    @SerializedName("imageUrl") val imageUrl: ArrayList<String>,
    @SerializedName("contents") val contents: String,
    @SerializedName("createdAt") val createdDate: String,
    @SerializedName("tagName") val hashTags: ArrayList<String>,
    @SerializedName("starRate") val starRate: Double
)