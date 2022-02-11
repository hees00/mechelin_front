package com.example.mechelin.ui.main.review

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<Review>
)
