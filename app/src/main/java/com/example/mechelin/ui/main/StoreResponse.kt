package com.example.mechelin.ui.main

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<StoreResult>
)
